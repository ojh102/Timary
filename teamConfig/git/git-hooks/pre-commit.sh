#!/bin/sh

echo "Running static analysis..."

# Validate Kotlin code with detekt and KtLint before committing
./gradlew ktlint detekt

status=$?

if [ "$status" = 0 ] ; then
    echo "Static analysis found no issues. Proceeding with committing."
    exit 0
else
    echo 1>&2 "Static analysis found issues you need to fix before committing."
    exit 1
fi