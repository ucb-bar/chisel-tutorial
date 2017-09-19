#!/usr/bin/env bash
args=$@
sbt "test:run-main problems.Launcher $args"
