rootProject.name = "bar4tix"
include(
    "libs:core-domain", "libs:core-contracts", "libs:core-commons", "libs:core-security",
    "libs:core-messaging", "libs:core-persistence", "libs:core-web",
    "gateway:api-gateway",
    "apps:bff-app", "apps:identity-service", "apps:profile-service", "apps:catalog-service",
    "apps:places-service", "apps:events-service",
    "apps:outbox-publisher", "apps:place-resolver-worker", "apps:bi-sink-worker"
)
include("tests")
