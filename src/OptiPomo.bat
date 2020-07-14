@echo off
WinSet, Style, -0xC00000, A  ; Remove the active window's title bar (WS_CAPTION).
mode con: cols=32 lines=4
color 3b
title OptiPomo
cls
java TimeHandler