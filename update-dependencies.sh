#!/bin/bash

set -e

SECONDS=0

echo "update-dependencies started"

DIRECTORIES=("." "packages/gatsby" "packages/gridsome" "packages/nextjs-with-fetch" "packages/nextjs-with-use-formspark" "packages/nuxtjs-with-fetch" "packages/react-with-fetch" "packages/react-with-use-formspark")

for i in "${DIRECTORIES[@]}"; do
  echo "Updating node_modules for $i"
  (cd "$i" && npm update --save)
done

duration=$SECONDS

echo "update-dependencies finished ($duration seconds)"
