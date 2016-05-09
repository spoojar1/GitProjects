@echo off
setlocal
 
dir /b "C:\Program Files\Java\jdk*" | findstr "jdk" > tmp.txt
for /f "tokens=1" %%g in (tmp.txt) do (
    @echo Output: %%g
    set JAVAVER=%%g
)
 
@echo %JAVAVER%
 
@echo %str%
 
set PATH=C:\Program Files\Java\%JAVAVER%\bin
 
@echo %PATH%
javac MutiCore_Assgnmt1.java
java MutiCore_Assgnmt1
cd %PATH%
endlocal

pause>nul