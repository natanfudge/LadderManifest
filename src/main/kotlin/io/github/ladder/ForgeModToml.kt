package io.github.ladder

class ForgeModToml(
    // Mandatory
    /**
     * A version range to match for said mod loader - for regular FML @Mod it will be the forge version
     */
    val loaderVersion: String,

    // Optional
    /**
     * A URL to refer people to when problems occur with this mod
     */
    val issueTrackerUrl: String?,
    /**
     * A list of mods - how many allowed here is determined by the individual mod loader
     */
    val mods: List<Mod>,
    /**
     * Dependencies are optional.
     */
    val dependencies: Map<String,List<Mod.Dependency>>
) {
    /**
     * The name of the mod loader type to load - for regular FML @Mod mods it should be javafml
     */
    val modLoader: String = "javafml"

    class Mod(
        // Mandatory
        /**
         * The modid of the mod
         */
        val modId: String,
        /**
         * The version number of the mod - there's a few well known ${} variables useable here or just hardcode it
         */
        val version: String,
        /**
         * A display name for the mod
         */
        val displayName: String,
        /**
         * The description text for the mod (multi line!)
         */
        val description: String,

        // Optional
        /**
         *  A URL to query for updates for this mod
         */
        val updateJSONURL: String?,
        /**
         * A URL for the "homepage" for this mod, displayed in the mod UI
         */
        val displayURL: String?,
        /**
         * A file name (in the root of the mod JAR) containing a logo for display
         */
        val logoFile: String?,
        /**
         * A text field displayed in the mod UI
         */
        val credits: String?,
        /**
         * A text field displayed in the mod UI
         */
        val authors: String?
    ) {

        class Dependency(
            // Mandatory

            /**
             * The modid of the dependency
             */
            val modId: String,
            /**
             *  Does this dependency have to exist - if not, ordering below must be specified
             */
            val mandatory: Boolean,
            /**
             * The version range of the dependency
             */
            val versionRange: String,
            /**
             * An ordering relationship for the dependency - BEFORE or AFTER required if the relationship is not mandatory
             */
            val ordering: Ordering,
            /**
             * Side this dependency is applied on - BOTH, CLIENT or SERVER
             */
            val side: Side
        ) {
            enum class Ordering {
                /**
                 * Serialized as NONE
                 */
                None,

                /**
                 * Serialized as BEFORE
                 */
                Before,

                /**
                 * Serialized as AFTER
                 */
                After
            }

            enum class Side {
                /**
                 * Serialized as BOTH
                 */
                Both,

                /**
                 * Serialized as CLIENT
                 */
                Client,

                /**
                 * Serialized as SERVER
                 */
                Server

            }
        }

    }
}