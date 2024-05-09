#!/bin/bash

# Criar exchanges
rabbitmqctl exchange declare -t topic pagamento-request-exchange
# Criar queues
rabbitmqctl queue declare -n pagamento-request-queue -d true
# Criar bindings
rabbitmqctl queue bind pagamento-request-queue pagamento-request-exchange pagamento-request-rout-key