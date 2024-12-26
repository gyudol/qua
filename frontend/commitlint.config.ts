import type { UserConfig } from "@commitlint/types";

const Configuration: UserConfig = {
  extends: ["gitmoji"],
  rules: {
    "type-case": [2, "always", "sentence-case"],
    "type-enum": [
      2,
      "always",
      [
        "Feat", // New feature
        "Fix", // Bug fix
        "Docs", // Documentation changes
        "Style", // Changes that do not affect the meaning of the code (white-space, formatting, etc.)
        "Refactor", // Code changes that neither fix a bug nor add a feature
        "Perf", // Performance improvement
        "Test", // Adding missing tests or correcting existing tests
        "Build", // Changes that affect the build system or external dependencies (example scopes: npm)
        "Ci", // Changes to CI configuration files and scripts
        "Chore", // Other changes that don't modify src or test files
        "Revert", // Reverts a previous commit
        "Wip", // Working in progress
      ],
    ],
    "scope-enum": [
      2,
      "always",
      [
        "setup", // Project setup
        "config", // Configuration files
        "deps", // Dependency updates
        "feature", // Feature-specific changes
        "bug", // Bug fixes
        "docs", // Documentation
        "style", // Code style/formatting
        "refactor", // Code refactoring
        "test", // Tests
        "build", // Build scripts or configuration
        "ci", // Continuous integration
        "release", // Release related changes
        "other", // Other changes
      ],
    ],
  },
};

export default Configuration;
