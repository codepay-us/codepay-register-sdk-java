# Codepay Register SDK for Java

## 1. About this SDK
Help Java developers quickly use Register SDK to achieve LAN cross-device service calls. Developers do not need to care about the complexity of the internal logic , just call a method to complete a functional operation .

## 2. Function highlights
Compared with API interface development, RegisterSDK integration is easier, providing function method calls without understanding serial or socket communication protocols. Register SDK build-in an ECRHub service module can provide better commercial experience, with the following highlights:
- **Automatic network discovery**
    - In WLAN mode, POS devices and terminals on the same local network can be automatically discovered without having to manually configure IP addresses and ports, and are not affected by changing IP addresses.
    - In USB mode, checking for plugged and unplugged USB cables is supported, and serial ports are automatically detected. Once an available serial port is found, it is automatically connected.
- **Secure pairing**
    - A pairing mechanism is only for WLAN mode. It provided to ensure communication security within the local network. It also better isolates connection when there are multiple POS devices or multiple terminals in the store.
    - The pairing operation supports initiated from the terminal (requires administrator permissions), and the POS end automatically confirms without any operation; it also supports initiating from the POS end, and the terminal manually confirms, but the developer needs to develop a pairing management function.
- **Connection management**
    - In WLAN mode, real-time network connection status checks are provided; transactions are automatically re-sent after the network is restored.
    - In USB enhanced mode, a heart beat detection mechanism is provided to real-time monitor the network connection status; transactions are automatically re-sent after the network is restored.

## 3. Getting Started

### 3.1 Prerequisite
- JDK version 1.8 and above development environment.
- Desktop computer or device running Windows, MacOS or Linux operating systems.

### 3.2 Installation & Configuration
1. Application installation and settings, please refer to <a href="https://developer.codepay.us/docs/UsbMode#development-guidelines" target="_blank">USB mode integration</a> and <a href="https://developer.codepay.us/docs/WlanMode" target="_blank">WLAN/LAN mode integration</a>.

2. Download the jar package and add it to your project, please refer to the GitHub source code.

| Source code & Library                                                |    WLAN Mode     | USB Mode  |
|------------------------------------------------------------------------------------|:------------------------------:|:-----------------------:|
| https://github.com/codepay-us/codepay-register-sdk-java                            | Windows/Linux/MacOS |   Windows    |
| https://github.com/codepay-us/codepay-register-cross-terminal-integration-demo-java |              Windows/Linux/MacOS                  |      Windows                   |

3. Maven dependencies
> The SDK depends on some open-source third-party jars. If these jars are not integrated into your project, you will need to manually add dependencies to your project.

```xml
<!-- Mandatory -->
<!-- jSerialComm -->
<dependency>
    <groupId>com.fazecast</groupId>
    <artifactId>jSerialComm</artifactId>
    <version>[2.0.0,3.0.0)</version>
</dependency>
<!-- WebSocket -->
<dependency>
    <groupId>org.java-websocket</groupId>
    <artifactId>Java-WebSocket</artifactId>
    <version>1.5.4</version>
</dependency>
<!-- fastjson2 -->
<dependency>
    <groupId>com.alibaba.fastjson2</groupId>
    <artifactId>fastjson2</artifactId>
    <version>2.0.26</version>
</dependency>
<!-- hutool -->
<dependency>
    <groupId>cn.hutool</groupId>
    <artifactId>hutool-all</artifactId>
    <version>5.8.21</version>
</dependency>
<!-- slf4j-api -->
<dependency>
    <groupId>org.slf4j</groupId>
    <artifactId>slf4j-api</artifactId>
    <version>2.0.9</version>
</dependency>
<!-- jmdns -->
<dependency>
  <groupId>org.jmdns</groupId>
  <artifactId>jmdns</artifactId>
  <version>3.5.8</version>
</dependency>
<!-- non-mandatory -->
<!-- logback -->
<dependency>
    <groupId>ch.qos.logback</groupId>
    <artifactId>logback-classic</artifactId>
    <version>1.3.11</version>
</dependency>
<!-- junit -->
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.8.1</version>
    <scope>test</scope>
</dependency>
```

## 4. Function List

### 4.1 Device discovery and pairing
> Only WLAN connection mode requires pairing first, USB connection mode does not require pairing.

#### 4.1.1 Start/Stop Device Discovery Service
The terminal can only discover your POS application when the device discovery service is started
- After completing a pairing operation, the terminal and POS application will record each other's network information
- When pairing and connecting to the network are required, the device discovery service needs to be enabled

```java
import com.codepay.register.sdk.device.ECRHubDiscoveryService;
import com.codepay.register.sdk.device.ECRHubWebSocketDiscoveryService;

ECRHubDiscoveryService service = ECRHubWebSocketDiscoveryService.getInstance();
//start device discover service
service.start();

//stop device discover service
service.stop();
```

#### 4.1.2 Get a list of paired terminals
- When using POS applications to push orders, you can select a device from the paired device list to push the order;
- When POS applications need to display paired POS terminals, this function can be used to obtain a list of paired devices for display.

```java
import com.codepay.register.sdk.device.ECRHubDevice;
import com.codepay.register.sdk.device.ECRHubDiscoveryService;
import com.codepay.register.sdk.device.ECRHubWebSocketDiscoveryService;

ECRHubDiscoveryService service = ECRHubWebSocketDiscoveryService.getInstance();

List<ECRHubDevice> pairedDeviceList = service.getPairedDeviceList();
for (ECRHubDevice ecrHubDevice : pairedDeviceList) {
    System.out.println("terminal_sn:" + ecrHubDevice.getTerminal_sn());
    System.out.println("ws_address:" + ecrHubDevice.getWs_address());
    
    // Create ECRHubClient instance object based on ws_address
    // ECRHubClient client = ECRHubClientFactory.create(ecrHubDevice.getWs_address());
}
```

#### 4.1.3 Remove paired terminals
When the POS terminal is no longer in use, it can be manually removed from the paired list of the POS application.

```java
import com.codepay.register.sdk.device.ECRHubDevice;
import com.codepay.register.sdk.device.ECRHubDiscoveryService;
import com.codepay.register.sdk.device.ECRHubWebSocketDiscoveryService;

ECRHubDiscoveryService service = ECRHubWebSocketDiscoveryService.getInstance();
List<ECRHubDevice> pairedDeviceList = service.getPairedDeviceList();

// Select the device to be removed for removal
ECRHubDevice device = pairedDeviceList.get(0);
service.unpair(device);
```

### 4.2 Connect
Select a paired terminal to initiate a network connection, and once the connection is established, transaction instructions can be sent.

#### 4.2.1 Call process
<ImageZoom src={rigiserConnect}/>

#### 4.2.2 Create client instance

**WLAN connection mode**
> When a POS application connects to a POS terminal using WLAN, use the following method to create a client

```java
import com.codepay.register.sdk.ECRHubClient;
import com.codepay.register.sdk.ECRHubConfig;
import com.codepay.register.sdk.ECRHubClientFactory;

// Create a client instance
ECRHubConfig config = new ECRHubConfig();
// Setting socket timeout
config.getSocketConfig().setConnTimeout(10 * 1000);
config.getSocketConfig().setWriteTimeout(10 * 1000);
config.getSocketConfig().setReadTimeout(120 * 1000);

// Please replace "xxxxxx" with the real host and port
ECRHubClient client = ECRHubClientFactory.create("ws://xxxxxx", config);
```

**USB connection mode**
> When a POS application connects to a POS terminal using a USB cable, use the following method to create a client

```java
import com.codepay.register.sdk.ECRHubClient;
import com.codepay.register.sdk.ECRHubConfig;
import com.codepay.register.sdk.ECRHubClientFactory;

// Create a client instance By Serial port
ECRHubConfig config = new ECRHubConfig();
// Setting Serial Port timeout
config.getSerialPortConfig().setConnTimeout(10 * 1000);
config.getSerialPortConfig().setWriteTimeout(10 * 1000);
config.getSerialPortConfig().setReadTimeout(120 * 1000);

// Method 1: Specify the serial port name. Please replace "xxxxxx" with the real serial port name. For example: COM6
// ECRHubClient client = ECRHubClientFactory.create("sp://xxxxxx", config);

// Method 2: Do not specify the serial port name. The SDK will automatically find available serial port
ECRHubClient client = ECRHubClientFactory.create("sp://", config);
```

#### 4.2.3 Connection
Establish a connection between the POS application and the POS terminal.

```java
// Connecting to the POS Terminal
client.connect();
```

#### 4.2.4 Disconnect
Disconnect the POS application from the POS terminal.

```java
// This will try disconnect from POS Terminal
client.disconnect();
```

### 4.3 Transactions

#### 4.3.1 Sale

1. <a href="https://developer.codepay.us/docs/CodePayRegisterSDK#61-sale">Request/Response parameters</a>
2. Example:

```java
import com.codepay.register.sdk.model.request.SaleRequest;
import com.codepay.register.sdk.model.response.SaleResponse;

// Build sale request
SaleRequest request = new SaleRequest();
request.setApp_id("Your payment appid"); // Setting your payment application ID
request.setMerchant_order_no("O123456789");
request.setOrder_amount("1");
request.setPay_scenario(EPayScenario.SWIPE_CARD.getVal());

// Setting read timeout,the timeout set here is valid for this request
ECRHubConfig requestConfig = new ECRHubConfig();
requestConfig.getSerialPortConfig().setReadTimeout(2 * 60 * 1000);
request.setConfig(requestConfig);
        
// Execute sale request
System.out.println("Sale Request:" + request);
SaleResponse response = client.execute(request);
System.out.println("Sale Response:" + response);
```

#### 4.3.2 Sale with cashback

1. <a href="https://developer.codepay.us/docs/CodePayRegisterSDK#62-sale-with-cashback">Request/Response parameters</a>
2. Example:

```java
import com.codepay.register.sdk.model.request.SaleRequest;
import com.codepay.register.sdk.model.response.SaleResponse;

// Build sale request
import com.codepay.register.sdk.model.request.SaleWithCashbackRequest;
import com.codepay.register.sdk.model.response.SaleWithCashbackResponse;

// Build sale with cashback request
SaleWithCashbackRequest request = new SaleWithCashbackRequest();
request.setApp_id("Your payment appid"); // Setting your payment application ID
request.setMerchant_order_no("O123456789");
request.setOrder_amount("50");
request.setCashback_amount("20");
request.setPay_scenario(EPayScenario.SWIPE_CARD.getVal());

// Setting read timeout,the timeout set here is valid for this request
ECRHubConfig requestConfig = new ECRHubConfig();
requestConfig.getSerialPortConfig().setReadTimeout(2 * 60 * 1000);
request.setConfig(requestConfig);
        
// Execute sale with cashback request
System.out.println("Sale Request:" + request);
SaleWithCashbackResponse response = client.execute(request);
System.out.println("Sale Response:" + response);
```

#### 4.3.3 Void

1. <a href="https://developer.codepay.us/docs/CodePayRegisterSDK#63-void">Request/Response parameters</a>
2. Example:

```java
import com.codepay.register.sdk.model.request.VoidRequest;
import com.codepay.register.sdk.model.response.VoidResponse;

// Build void request
VoidRequest request = new VoidRequest();
request.setApp_id("Your payment appid"); // Setting your payment application ID
request.setMerchant_order_no("O123456789");
        
// Execute void request
System.out.println("Void Request:" + request);
VoidResponse response = client.execute(request);
System.out.println("Void Response:" + response);
```

#### 4.3.4 Refund

1. <a href="https://developer.codepay.us/docs/CodePayRegisterSDK#64-refund">Request/Response parameters</a>
2. Example:

```java
import com.codepay.register.sdk.model.request.RefundRequest;
import com.codepay.register.sdk.model.response.RefundResponse;

// Build refund request
RefundRequest request = new RefundRequest();
request.setApp_id("Your payment appid"); // Setting your payment application ID
request.setOrig_merchant_order_no("O1695032342508");// The merchant order number of the original order
request.setMerchant_order_no("O123456789");
request.setOrder_amount("1");
request.setPay_scenario(EPayScenario.SWIPE_CARD.getVal());
        
// Execute refund request
System.out.println("Refund Request:" + request);
RefundResponse response = client.execute(request);
System.out.println("Refund Response:" + response);
```

#### 4.3.5 Authorization

1. <a href="https://developer.codepay.us/docs/CodePayRegisterSDK#65-authorization">Request/Response parameters</a>
2. Example:

```java
import com.codepay.register.sdk.model.request.AuthRequest;
import com.codepay.register.sdk.model.response.AuthResponse;

// Build authorization request
AuthRequest request = new AuthRequest();
request.setApp_id("Your payment appid"); // Setting your payment application ID
request.setMerchant_order_no("O123456789");
request.setOrder_amount("1");
request.setPay_scenario(EPayScenario.SWIPE_CARD.getVal());
        
// Execute authorization request
System.out.println("Authorization Request:" + request);
AuthResponse response = client.execute(request);
System.out.println("Authorization Response:" + response);
```

#### 4.3.6 Completion

1. <a href="https://developer.codepay.us/docs/CodePayRegisterSDK#66-completion">Request/Response parameters</a>
2. Example:

```java
import com.codepay.register.sdk.model.request.CompletionRequest;
import com.codepay.register.sdk.model.response.CompletionResponse;

// Build completion request
CompletionRequest request = new CompletionRequest();
request.setApp_id("Your payment appid"); // Setting your payment application ID
request.setOrig_merchant_order_no("O1695032342508");// The merchant order number of the original Authorization order
request.setMerchant_order_no("O123456789");
request.setOrder_amount("1");
request.setPay_scenario(EPayScenario.SWIPE_CARD.getVal());
        
// Execute completion request
System.out.println("Completion Request:" + request);
CompletionResponse response = client.execute(request);
System.out.println("Completion Response:" + response);
```

#### 4.3.7 Query

1. <a href="https://developer.codepay.us/docs/CodePayRegisterSDK#67-query">Request/Response parameters</a>
2. Example:

```java
import com.codepay.register.sdk.model.request.QueryRequest;
import com.codepay.register.sdk.model.response.QueryResponse;

// Build query request
QueryRequest request = new QueryRequest();
request.setApp_id("Your payment appid"); // Setting your payment application ID
request.setMerchant_order_no("O123456789");

// Execute query request
System.out.println("Query Request:" + request);
QueryResponse response = client.execute(request);
System.out.println("Query Response:" + response);
```

#### 4.3.8 Close

1. <a href="https://developer.codepay.us/docs/CodePayRegisterSDK#68-close">Request/Response parameters</a>
2. Example:

```java
import com.codepay.register.sdk.model.request.CloseRequest;
import com.codepay.register.sdk.model.response.CloseResponse;

// Build close request
CloseRequest request = new CloseRequest();
request.setApp_id("Your payment appid"); // Setting your payment application ID
request.setMerchant_order_no("O123456789");

// Execute close request
System.out.println("Close Request:" + request);
CloseResponse response = client.execute(request);
System.out.println("Close Response:" + response);
```