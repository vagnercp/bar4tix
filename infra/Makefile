COMPOSE = docker compose
MIG_SERVICES = flyway-infra flyway-catalog flyway-places flyway-events flyway-idp flyway-prof flyway-vapp

.PHONY: migrate info

migrate:
	$(COMPOSE) up -d postgres
	# espera o banco ficar healthy (usa o próprio container do postgres)
	$(COMPOSE) exec -T postgres pg_isready -h localhost -U bar4tix -d bar4tix
	@for s in $(MIG_SERVICES); do \
		echo ">>> $$s :: migrate"; \
		$(COMPOSE) --profile migrations run --rm $$s || exit $$?; \
	done



info:
	@set -e; \
	for svc in flyway-infra flyway-catalog flyway-places flyway-events flyway-idp flyway-prof flyway-vapp; do \
	  case $$svc in \
	    flyway-infra)   ARGS="-locations=filesystem:/flyway/sql/00_infra -defaultSchema=public" ;; \
	    flyway-catalog) ARGS="-locations=filesystem:/flyway/sql/catalog/db -defaultSchema=catalog -schemas=catalog -table=flyway_schema_history" ;; \
	    flyway-places)  ARGS="-locations=filesystem:/flyway/sql/places/db  -defaultSchema=places  -schemas=places  -table=flyway_schema_history" ;; \
	    flyway-events)  ARGS="-locations=filesystem:/flyway/sql/events/db  -defaultSchema=evt     -schemas=evt     -table=flyway_schema_history" ;; \
	    flyway-idp)     ARGS="-locations=filesystem:/flyway/sql/idp/db     -defaultSchema=idp     -schemas=idp     -table=flyway_schema_history" ;; \
	    flyway-prof)    ARGS="-locations=filesystem:/flyway/sql/prof/db    -defaultSchema=prof    -schemas=prof    -table=flyway_schema_history" ;; \
	    flyway-vapp)    ARGS="-locations=filesystem:/flyway/sql/vapp/db    -defaultSchema=vapp    -schemas=vapp    -table=flyway_schema_history" ;; \
	  esac; \
	  echo ""; \
	  echo "===================================================================="; \
	  echo ">>> $$svc"; \
	  echo "--------------------------------------------------------------------"; \
	  $(COMPOSE) --profile migrations run --rm $$svc $$ARGS info 2>/dev/null \
	    | grep -E '^(Schema version:|[+|])' \
	    | sed '/^$$/d' || true; \
	done