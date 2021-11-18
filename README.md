# Sobre
	Uma aplica��o maven project utilizando um produtor client kafka para enviar mensagens para um t�pico

## Passos para reprodu��o
### Configure WSL2 seguindo este artigo:
	https://www.confluent.io/blog/set-up-and-run-kafka-on-windows-linux-wsl-2/#enable-windows-subsystem

### Em um terminal do WSL, obtenha o IP ao qual a m�quina virtual responde:
	ip addr show eth0 | grep -oP '(?<=inet\s)\d+(\.\d+){3}'

### Altere a propriedade listeners dentro de config/server.properties do kafka dentro do WSL:
	listeners=PLAINTEXT://ip-encontrado-acima:9092

### Na aplica��o JAVA rodando no Windows:
	Na propriedade ProducerConfig.BOOTSTRAP_SERVERS_CONFIG com o valor <ip-encontrado-acima>:9092

## OBS:
	Sempre quando o windows � reiniciado, o IP na WSL � alterado.
