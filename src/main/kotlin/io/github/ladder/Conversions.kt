package io.github.ladder

import com.github.michaelbull.result.Err
import com.github.michaelbull.result.Ok
import com.github.michaelbull.result.Result
import com.moandjiezana.toml.TomlWriter
import kotlinx.serialization.json.Json
import kotlinx.serialization.json.JsonConfiguration
import java.io.File

typealias MayFail<T> = Result<T, String>

fun ForgeModToml.toToml(): String = TomlWriter().write(this)

//TODO: make all of the nullable values in fabric json default to null so they will not be encoded when null
val json = Json(JsonConfiguration.Stable.copy(prettyPrint = true, encodeDefaults = false))
fun FabricModJson.toJson(): String = json.stringify(FabricModJson.serializer(), this)

fun ForgeModToml.toFabric(): MayFail<FabricModJson> {
    if (mods.isEmpty()) return Err("Mod TOML does not contain any mods")
    if (mods.size > 1) {
        return Err("Mod TOML has more than one mod. This is not supported by the Fabric loader. Make a separate project for each mod.")
    }
    with(mods[0]) {
        val json = FabricModJson(
            id = modId,
            suggests = null,
        )

        return Ok(json)
    }


}

fun main() {
    val testToml = ForgeModToml(
        loaderVersion = "forgeversion",
        issueTrackerUrl = null,
        mods = listOf(
            ForgeModToml.Mod(
                modId = "modid",
                description = "desc",
                displayName = "name",
                version = "vers",
                authors = null,
                credits = null,
                displayURL = null,
                logoFile = null,
                updateJSONURL = null

            )
        ),
        dependencies = mapOf(
            "b" to listOf(
                ForgeModToml.Mod.Dependency(
                    modId = "c",
                    mandatory = true,
                    ordering = ForgeModToml.Mod.Dependency.Ordering.None,
                    side = ForgeModToml.Mod.Dependency.Side.Both,
                    versionRange = "d"
                )
            )
        )
    )

    File("testToml.toml").writeText(testToml.toToml())

    val testJson = FabricModJson(
        id = "modid",
        version = "vers",
        description = "desc",
        authors = null,
        name = "name",
        breaks = null,
        conflicts = null,
        contact = null,
        contributors = null,
        custom = null,
        depends = null,
        entrypoints = null,
        environment = null,
        icon = null,
        license = null,
        recommends = null,
        suggests = null
    )

    File("testJson.json").writeText(testJson.toJson())
}