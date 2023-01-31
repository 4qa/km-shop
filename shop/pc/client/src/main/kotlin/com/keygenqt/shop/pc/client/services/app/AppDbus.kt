package com.keygenqt.shop.pc.client.services.app

import com.keygenqt.shop.pc.client.interfaces.IMethod
import org.freedesktop.dbus.annotations.DBusInterfaceName
import org.freedesktop.dbus.interfaces.DBusInterface
import org.freedesktop.dbus.types.UInt32

enum class AppDbusMethods(override val value: String) : IMethod {
    INIT_SUCCESS("inInitSuccess"),
    INIT_ERROR("inInitError"),
    UPDATE_ORDER("inUpdateOrder"),
    UPDATE_HELP("inUpdateHelp"),
}

@DBusInterfaceName("local.app.AppDbus")
interface AppDbus : DBusInterface {
    fun inInitSuccess(secret: String, countNewOrder: UInt32, countHelpNotChecked: UInt32)
    fun inInitError(secret: String, value: UInt32, error: String)
    fun inUpdateOrder(secret: String, value: UInt32)
    fun inUpdateHelp(secret: String, value: UInt32)
}