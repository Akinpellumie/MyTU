
package com.akinpelumi.c2068220.mytu.service

interface LogService {
  fun logNonFatalCrash(throwable: Throwable)
}
