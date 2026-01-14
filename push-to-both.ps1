# PowerShell script to push to both GitHub and Gitee

Write-Host "Pushing to GitHub..." -ForegroundColor Green
git push origin master

Write-Host "Pushing to Gitee..." -ForegroundColor Green
git push gitee master

Write-Host "Push completed to both repositories!" -ForegroundColor Green
