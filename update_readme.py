import os
import datetime
from github import Github

# GitHub 토큰 및 리포지토리 정보
GITHUB_TOKEN = os.getenv('GITHUB_TOKEN')
REPO_NAME = 'wda067/coding-test-study'  # 예: 'octocat/hello-world'

# 팀원 GitHub 사용자명
TEAM_MEMBERS = ['wda067', 'daewon-ko', 'pkl0912', 'yeonjy']

# 요일 매핑
DAYS_OF_WEEK = ['월요일', '화요일', '수요일', '목요일', '금요일', '토요일', '일요일']

def get_week_number_and_year(date):
    """주어진 날짜의 ISO 연도와 몇째 주인지 반환"""
    iso_year, week_number, _ = date.isocalendar()
    """목요일을 기준으로 몇월인지 계산"""
    first_day_of_week = datetime.date.fromisocalendar(iso_year, week_number, 4)
    return iso_year, week_number, first_day_of_week.month

def get_pr_status():
    """PR 상태를 가져오고, 머지된 날짜를 기준으로 요일별 상태 업데이트"""
    g = Github(GITHUB_TOKEN)
    repo = g.get_repo(REPO_NAME)
    pr_status = {member: [''] * 7 for member in TEAM_MEMBERS}  # 기본값은 빈 문자열로 설정

    pulls = repo.get_pulls(state='closed', sort='created', direction='asc')
    current_year, current_week, current_month = get_week_number_and_year(datetime.datetime.now())

    # closed pr을 탐색
    for pr in pulls:
        # 머지한 스터디원의 pr이라면
        if pr.user.login in TEAM_MEMBERS and pr.merged:
            merged_date = pr.merged_at
            if merged_date:
                merged_year, merged_week, merged_month = get_week_number_and_year(merged_date)
                # 머지한 날의 스터디원 체크
                if  merged_year == current_year and merged_week == current_week and merged_month == current_month:
                    day_index = merged_date.weekday()  # 월요일=0, 일요일=6

                    for member in pr_status:
                        for i in range(day_index + 1):
                            if pr_status[member][i] == '':  # 만약 빈 값이면
                                pr_status[member][i] = '❌'
                    pr_status[pr.user.login][day_index] = '✔️'  # 머지된 PR은 '✔️'로 표시

    return pr_status

def update_readme(pr_status):
    """README.md를 업데이트, PR 상태 및 주차 정보 갱신"""
    current_date = datetime.datetime.now()
    iso_year, week_number, iso_month = get_week_number_and_year(current_date)  # ISO 연도와 주차 계산

    with open('README.md', 'r') as file:
        lines = file.readlines()

    # 주차 정보 갱신
    week_marker = '## '
    try:
        week_index = next(i for i, line in enumerate(lines) if line.startswith(week_marker))
        lines[week_index] = f'## 📅 {iso_year}년 {iso_month}월 {week_number}주차\n'
    except StopIteration:
        print("주차 정보를 갱신할 섹션을 찾을 수 없습니다.")
        return

    # PR 상태 표 갱신
    start_marker = '| 스터디원 | 월요일 | 화요일 | 수요일 | 목요일 | 금요일 | 토요일 | 일요일 |\n'
    end_marker = '|---------|:------:|:------:|:------:|:------:|:------:|:------:|:------:|\n'

    try:
        start_index = lines.index(start_marker) + 2
        end_index = start_index + len(TEAM_MEMBERS)
    except ValueError:
        print("표 형식을 찾을 수 없습니다.")
        return

    for i, member in enumerate(TEAM_MEMBERS):
        status_line = f"| {member} | {' | '.join(pr_status[member])} |\n"
        lines[start_index + i] = status_line

    with open('README.md', 'w') as file:
        file.writelines(lines)

if __name__ == "__main__":
    pr_status = get_pr_status()
    update_readme(pr_status)
