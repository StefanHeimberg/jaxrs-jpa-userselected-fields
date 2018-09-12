#!/usr/bin/env sh

set -e

__dir="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd ${__dir}/

time ./mvnw clean verify
