package ru.honfate.upgrowth.core.api.field

interface GoodsFunctions {

    fun growthFood(location: Location) : Int
    fun growthCards(location: Location, player: Player): Int
    fun growthResources(location: Location): Int
}