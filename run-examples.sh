#!/usr/bin/env bash
args=$@
sbt -v "test:runMain examples.Launcher $args"
