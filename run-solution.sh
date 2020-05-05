#!/usr/bin/env bash
args=$@
sbt "test:runMain solutions.Launcher $args"
