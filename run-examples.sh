#!/usr/bin/env bash
args=$@
sbt -v "test:run-main examples.Launcher $args"
