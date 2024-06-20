
Build all

`mvn -T 2.0C clean install -DskipTests -Pinclude-grpc`

Only build gcp + assembly (to .zip)

`mvn -T 2.0C package -DskipTests -Pinclude-grpc -pl :nifi-gcp-processors -pl :nifi-assembly`

a zip file is generated here

`nifi-assembly/target/nifi-1.21.1-SNAPSHOT-bin.zip`


## Change version

mvn versions:set -DnewVersion=1.21.1-SNAPSHOT
mvn versions:commit -Pinclude-grpc



