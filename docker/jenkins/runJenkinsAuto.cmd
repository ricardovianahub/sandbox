docker run --rm -v //var/run/docker.sock:/var/run/docker.sock alpine stat -c %%g /var/run/docker.sock > dockerGroupId.txt
set /p groupid =< dockerGroupId.txt
docker run --rm --name jenkinsauto -p:8098:8080 --group-add %groupid% -v //var/run/docker.sock:/var/run/docker.sock jenkinsauto
