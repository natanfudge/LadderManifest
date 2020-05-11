package io.github.ladder

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonElement

typealias VersionedMods = Map<String, String>

@Serializable
class FabricModJson(
    // Required
    /**
     * Defines the mod's identifier - a string of Latin letters, digits, underscores with length from 1 to 63.
     */
    val id: String,
    /**
     * Defines the mod's version - a string value, optionally matching the Semantic Versioning 2.0.0 specification
     */
    val version: String,

    // Optional (mod loading)
    /**
     * Defines where mod runs: only on the client side (client mod), only on the server side (plugin) or on both sides (regular mod). Contains the environment identifier
     */
    val environment: Environment?,

    /**
     *  Defines main classes of your mod, that will be loaded.
     * There are 3 default entry points for your mod:
     *
     * **main**  Will be run first. For classes implementing ModInitializer.
     *
     * **client** Will be run second and only on the client side. For classes implementing ClientModInitializer.

     * **server** Will be run second and only on the server side. For classes implementing DedicatedServerModInitializer.

     * Each entry point can contain any number of classes to load. Classes (or methods or static fields) could be defined in two ways:
     */
    val entrypoints: Map<String, List<String>>?,

    // Optional (dependency resolution)
    /**
     *  For dependencies required to run. Without them a game will crash.
     */
    val depends: VersionedMods?,
    /**
     *  For dependencies not required to run. Without them a game will log a warning.
     */
    val recommends: VersionedMods?,
    /**
     *  For dependencies not required to run. Use this as a kind of metadata.
     */
    val suggests: VersionedMods?,
    /**
     * For mods whose together with yours might cause a game crash. With them a game will crash.
     */
    val breaks: VersionedMods?,
    /**
     * For mods whose together with yours cause some kind of bugs, etc. With them a game will log a warning.
     */
    val conflicts: VersionedMods?,

    // Optional (metadata)

    /**
     * Defines the user-friendly mod's name. If not present, assume it matches id.
     */
    val name: String?,
    /**
     * Defines the mod's description. If not present, assume empty string.
     */
    val description: String?,
    /**
     * Defines the contact information for the project. It is an object of the following fields:
     * - **email** Contact e-mail pertaining to the mod. Must be a valid e-mail address.
     * - **irc** IRC channel pertaining to the mod. Must be of a valid URL format - for example: irc://irc.esper.net:6667/charset for #charset at EsperNet - the port is optional, and assumed to be 6667 if not present.
     * - **homepage** Project or user homepage. Must be a valid HTTP/HTTPS address.
     * - **issues** Project issue tracker. Must be a valid HTTP/HTTPS address.
     * - **sources** Project source code repository. Must be a valid URL - it can, however, be a specialized URL for a given VCS (such as Git or Mercurial).
     * The list is not exhaustive - mods may provide additional, non-standard keys (such as discord, slack, twitter, etc) - if possible, they should be valid URLs.
     */
    val contact: Map<String, String>?,
    /**
     * A list of authors of the mod. Each entry is a single name.
     * NOTE: DOES NOT SUPPORT OBJECTS FOR THE "contact" FIELD
     */
    val authors: List<String>?,
    /**
     * A list of contributors to the mod. Each entry is a single name.
     * NOTE: DOES NOT SUPPORT OBJECTS FOR THE "contact" FIELD
     */
    val contributors: List<String>?,
    /**
     * Defines the licensing information.
     * NOTE: DOES NOT SUPPORT MULTIPLE LICENSES AS A LIST OF STRINGS
     */
    val license: String?,
    /**
     * Defines the mod's icon. Icons are square PNG files.
     * (Minecraft resource packs use 128×128, but that is not a hard requirement - a power of two is, however, recommended.)
     * Can be provided as a path to a single png file.
     * NOTE: DOES NOT SUPPORT MULTIPLE IMAGES WITH THEIR WIDTH AS A STRING -> STRING MAP
     */
    val icon: String?,

    /**
     * You can add any field you want to add inside custom field.
     * Loader would ignore them.
     * However it's highly recommended to namespace your fields to avoid conflicts if your fields (names) would be added to the standard specification.
     */
    val custom: Map<String, JsonElement>?

) {


    /**
     *  Needed for internal mechanisms. Must always be 1.
     */
    private val schemaVersion: Int = 1

    @Serializable
    enum class Environment {
        @SerialName("*")
        /**
         * Runs everywhere. Default.
         */
        Everywhere,

        /**
         * Runs on the client side.
         */
        @SerialName("client")
        Client,

        /**
         * Runs on the server side.
         */
        @SerialName("server")
        Server
    }

    /**
     * Not relevant for converting:
     *
     *
     *  val jars: List<Any>      | The values are generated at publish time
     *  val languageAdapters: Map<String,String>         | Field is pointless
     *  val mixins: List<Any>        | Not used in forge, also a pain to parse
     *  val accessWidener: String        | Forge AT format is different
     */

    //TODO: common solutions for:
    // - JIJ
    // - Mixins
    // - Access Wideners / ATs


}