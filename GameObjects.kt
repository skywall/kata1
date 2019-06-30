package cz.skywall.kata1

sealed class GameObject

object Goose : GameObject() {
    override fun toString() = ":husa"
}

object Fox : GameObject() {
    override fun toString() = ":liska"
}

object Corn : GameObject() {
    override fun toString() = ":zrni"
}

object Me : GameObject() {
    override fun toString() = ":ja"
}


