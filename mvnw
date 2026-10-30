#!/bin/sh
set -eu
MAVEN_VERSION="3.9.11"
WRAPPER_HOME="${MAVEN_USER_HOME:-$HOME/.m2}/wrapper/dists/apache-maven-${MAVEN_VERSION}"
MAVEN_BIN="${WRAPPER_HOME}/bin/mvn"
if [ ! -x "$MAVEN_BIN" ]; then
  mkdir -p "$WRAPPER_HOME"
  ARCHIVE="${TMPDIR:-/tmp}/apache-maven-${MAVEN_VERSION}-bin.tar.gz"
  if command -v curl >/dev/null 2>&1; then
    curl -fsSL "https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" -o "$ARCHIVE"
  elif command -v wget >/dev/null 2>&1; then
    wget -q "https://archive.apache.org/dist/maven/maven-3/${MAVEN_VERSION}/binaries/apache-maven-${MAVEN_VERSION}-bin.tar.gz" -O "$ARCHIVE"
  else
    echo "É necessário instalar Maven ou curl/wget para baixar o Maven Wrapper." >&2
    exit 1
  fi
  tar -xzf "$ARCHIVE" -C "$WRAPPER_HOME" --strip-components=1
  rm -f "$ARCHIVE"
fi
exec "$MAVEN_BIN" "$@"
