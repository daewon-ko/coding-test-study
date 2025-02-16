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
    """주어진 날짜의 연도와 몇째 주인지 반환 (목요일을 기준으로 월 변경)"""
    year = date.year

    # date를 datetime.date로 변환 (datetime.datetime이 들어오는 경우를 대비)
    date = date.date()

    # 현재 날짜가 속한 주의 목요일 찾기
    thursday_of_week = date + datetime.timedelta(days=(3 - date.weekday()))

    # 목요일 기준으로 월을 결정
    month = thursday_of_week.month

    # 해당 월의 첫 번째 날짜
    first_day_of_month = datetime.date(year, month, 1)
    # 첫 번째 월요일 찾기
    first_monday = first_day_of_month + datetime.timedelta(days=(0 - first_day_of_month.weekday()) % 7)

    # 주어진 날짜의 주차 계산
    days_difference = (date - first_monday).days
    week_number = (days_difference // 7) + 1

    return year, week_number, month

def extract_problem_count(title):
    """PR 제목에서 문제 수를 추출"""
    try:
        # 제목 형식: 'myeong-geun / 2025년 1월 20일 / 1문제'
        parts = title.split('/')
        if len(parts) >= 3:
            problem_count_str = parts[2].strip().split('문제')[0]
            return int(problem_count_str)
    except (ValueError, IndexError):
        return 0  # 문제 수를 추출하지 못하면 기본값으로 0 반환
    return 0

def get_pr_status():
    """PR 상태를 가져오고, PR 생성 날짜를 기준으로 요일별 상태 업데이트"""
    g = Github(GITHUB_TOKEN)
    repo = g.get_repo(REPO_NAME)
    pr_status = {member: [''] * 7 for member in TEAM_MEMBERS}  # 기본값은 빈 문자열로 설정

    pulls = repo.get_pulls(state='closed', sort='created', direction='asc')
    current_year, current_week, current_month = get_week_number_and_year(datetime.datetime.now())

    for pr in pulls:
        if pr.user.login in TEAM_MEMBERS:
            created_date = pr.created_at + datetime.timedelta(hours=9)  # PR 생성 시간을 KST로 변환
            problem_count = extract_problem_count(pr.title)
            if created_date and problem_count >= 2:  # 문제 수가 2 이상일 때만 체크
                created_year, created_week, created_month = get_week_number_and_year(created_date)
                if created_year == current_year and created_week == current_week and created_month == current_month:
                    day_index = created_date.weekday()  # 월요일=0, 일요일=6
                    for member in pr_status:
                        for i in range(day_index + 1):
                            if i == 5:
                                break
                            if pr_status[member][i] == '':  # 빈 값이면 '❌' 설정
                                pr_status[member][i] = '❌'
                    pr_status[pr.user.login][day_index] = '✔️'  # PR 생성된 날짜를 '✔️'로 표시

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
