# Lucas Sequence Microservices

**Autor:** Tomás Felipe Panqueva  
**Institución:** Escuela Colombiana de Ingeniería (ECI)

---

## Descripción

Arquitectura de microservicios distribuida en AWS que calcula la **Secuencia de Lucas**.

Componentes:
- **Math Services:** 2 instancias EC2 que computan la secuencia
- **Proxy:** Balanceador de carga (round-robin)
- **Cliente Web:** HTML5 + JavaScript

---

## Tecnologías

- Java 17 (Amazon Corretto)
- Spring Boot
- Maven
- HTML5 + JavaScript puro
- AWS EC2
- Git + GitHub

---

## Instalación en AWS EC2

### 1. Crear Infraestructura

- VPC (CIDR: 10.0.0.0/16)
- Subnet (CIDR: 10.0.1.0/24)
- Internet Gateway y Routing Table
- Security Group: SSH (22), HTTP (80), HTTPS (443)

### 2. Crear Instancias

3 instancias EC2 (Amazon Linux 2, t3.micro):
- 2 instancias: Math Services (IP privada)
- 1 instancia: Proxy (IP pública)

### 3. Instalar Dependencias

```bash
sudo yum update -y
sudo yum install java-17-amazon-corretto-devel -y
sudo yum install maven -y
sudo yum install git -y
```

### 4. Clonar Repositorio

```bash
cd /home/ec2-user
git clone https://github.com/t-hash06-tdse/partial-2.git
cd partial-2
```

### 5. Ejecutar Math Services

En cada instancia de Math Services:

```bash
cd /home/ec2-user/partial-2/math-services
mvn clean package -DskipTests
sudo mvn spring-boot:run -Dserver.port=80
```

### 6. Ejecutar Proxy

En la instancia del Proxy:

```bash
cd /home/ec2-user/partial-2/proxy

export INSTANCE_1="http://10.0.0.7:80"
export INSTANCE_2="http://10.0.0.12:80"
export SERVER_PORT=80

mvn clean package -DskipTests
sudo mvn spring-boot:run
```

### 7. Acceder

```
http://<IP-Publica-Proxy>
```

---

## Endpoints

**Math Services & Proxy:**
```
GET /lucasseq?value=n
```

Calcula los primeros `n` números de la Secuencia de Lucas.

Ejemplo:
```json
{
  "operation": "Secuencia de Lucas",
  "input": "7",
  "output": "2, 1, 3, 4, 7, 11, 18"
}
```

---

## Video de Demostración

> Puedes ver el video demostrativo [aquí](res/video.mp4).

---

## Repositorio

[https://github.com/t-hash06-tdse/partial-2](https://github.com/t-hash06-tdse/partial-2)

---

## Licencia

Proyecto académico - Escuela Colombiana de Ingeniería (ECI)
