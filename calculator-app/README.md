# Android 计算器应用

## 项目结构
`
calculator-app/
├── app/
│   ├── src/main/
│   │   ├── java/com/example/calculator/
│   │   │   └── MainActivity.java
│   │   ├── res/
│   │   │   ├── drawable/
│   │   │   │   ├── button_bg.xml
│   │   │   │   ├── button_op_bg.xml
│   │   │   │   └── button_eq_bg.xml
│   │   │   ├── layout/
│   │   │   │   └── activity_main.xml
│   │   │   └── values/
│   │   │       └── themes.xml
│   │   └── AndroidManifest.xml
│   └── build/
│       └── outputs/
│           └── apk/
└── README.md
`

## 功能特性
- 基础四则运算（加、减、乘、除）
- 百分比计算
- 正负号切换
- 小数点支持
- 连续运算
- 错误处理（除零保护）

## 构建步骤

### 方法一：使用 Android Studio（推荐）
1. 安装 Android Studio
2. 打开 calculator-app 文件夹
3. 等待 Gradle 同步完成
4. 点击 "Build" -> "Build Bundle(s) / APK(s)" -> "Build APK(s)"
5. APK 文件将生成在 pp/build/outputs/apk/ 目录下

### 方法二：使用命令行
`ash
cd calculator-app
./gradlew assembleDebug
`

## 运行
- 将 APK 文件传输到 Android 设备
- 启用"未知来源"应用安装
- 安装并运行"计算器"应用

## 技术栈
- 语言：Java
- 最小 SDK：API 21 (Android 5.0)
- 目标 SDK：API 33 (Android 13)
- 构建工具：Gradle 8.0+

## 注意事项
- 本应用需要 Android 5.0 或更高版本
- 首次安装可能需要授权存储权限
- 建议在生产环境签署 APK
