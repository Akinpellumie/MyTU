package com.akinpelumi.c2068220.mytu.service

interface ConfigurationService {
  suspend fun fetchConfiguration(): Boolean
}
