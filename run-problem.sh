#!/usr/bin/env bash
args=$@
sbt "test:runMain problems.Launcher $args"
