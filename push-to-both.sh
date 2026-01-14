#!/bin/bash

echo "Pushing to GitHub..."
git push origin master

echo "Pushing to Gitee..."
git push gitee master

echo "Push completed to both repositories!"
