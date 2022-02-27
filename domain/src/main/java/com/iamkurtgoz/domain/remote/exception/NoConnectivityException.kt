package com.iamkurtgoz.domain.data.remote.network.exception

import java.io.IOException

class NoConnectivityException :
    IOException("No network available, please check your WiFi or Data connection")