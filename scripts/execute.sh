#!/bin/bash

set -e

echo Enter a command to execute:
read -r COMMAND

SECONDS=0

echo "execute started"

DIRECTORIES=("." "packages/gatsby" "packages/gridsome" "packages/nextjs-with-fetch" "packages/nextjs-with-use-formspark" "packages/nuxtjs-with-fetch" "packages/react-with-fetch" "packages/react-with-use-formspark")

for i in "${DIRECTORIES[@]}"; do
  echo "Executing '$COMMAND' in $i"
  (cd "$i" && eval "$COMMAND")
done

duration=$SECONDS

echo "execute finished ($duration seconds)"
