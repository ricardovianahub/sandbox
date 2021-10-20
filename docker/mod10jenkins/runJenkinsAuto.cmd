rem docker run --rm -v //var/run/docker.sock:/var/run/docker.sock alpine stat -c %%g /var/run/docker.sock > groupid.dat
rem set /p groupid=<groupid.dat
rem echo value is %groupid%
docker run --rm --name jenkinsauto -p:8098:8080 --group-add 0 -v //var/run/docker.sock:/var/run/docker.sock jenkinsauto
