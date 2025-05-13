#!/usr/bin/env bash

# Dein Token
GMT_TOKEN='guihe_ASju4Fsa-FAshzh3zFH))afhhz'

# JSON-Payload
json_body='{"name":"C8-complex-long-test","email":"moritz.schwarz@envite.de","image_url":"","repo_url":"https://github.com/Moritz148/camunda8experiment.git","filename":"","branch":"complex_long","machine_id":"6","schedule_mode":"one-off"}'

# Einmaliger POST-Request
curl 'https://api.green-coding.io/v1/software/add' \
  -H 'Origin: https://metrics.green-coding.io/' \
  -H "X-Authentication: ${GMT_TOKEN}" \
  -H 'Content-Type: application/json' \
  --data-raw "$json_body"

echo "Einmaliger POST ausgeführt"
