@echo off
set MAVEN_VERSION=3.9.11
set WRAPPER_HOME=%USERPROFILE%\.m2\wrapper\dists\apache-maven-%MAVEN_VERSION%
set MAVEN_BIN=%WRAPPER_HOME%\bin\mvn.cmd
if not exist "%MAVEN_BIN%" (
  if not exist "%WRAPPER_HOME%" mkdir "%WRAPPER_HOME%"
  powershell -NoProfile -ExecutionPolicy Bypass -Command "$u='https://archive.apache.org/dist/maven/maven-3/%MAVEN_VERSION%/binaries/apache-maven-%MAVEN_VERSION%-bin.zip'; $z=Join-Path $env:TEMP 'apache-maven.zip'; Invoke-WebRequest -Uri $u -OutFile $z; Expand-Archive -Path $z -DestinationPath $env:TEMP -Force; Copy-Item -Path (Join-Path $env:TEMP 'apache-maven-%MAVEN_VERSION%\*') -Destination '%WRAPPER_HOME%' -Recurse -Force; Remove-Item $z"
)
call "%MAVEN_BIN%" %*
