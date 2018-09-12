#!/usr/bin/env sh

set -e

exec java -jar $PAYARA_MICRO_JAR \
     --sslport 8443 \
     --enablehealthcheck true \
     --disablephonehome \
     "$@"
