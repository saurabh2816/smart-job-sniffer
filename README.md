# smart-job-sniffer

# Problem
I was tired of looking for new jobs every day and then having to go through the long descriptions and qualifications. With so many jobs and requirements it's not easy to filter the right job for you.

# Solution 
I discovered that https://www.smartr.me which is a leading platform in hiring for tech jobs had an unprotected-API which gives results in JSON. 
So I created a scheduler in Spring Boot that fetches results from their API regularly and saves it in a MySQL table. Another scheduler runs to fetch the news jobs stored in the MySQL database and sends the new jobs to me on Slack.
These jobs are only saved if they match certain criteria like 
- If the job description contains "Java"
- Location of the job is in US


  
