<div align="center">
  <img src="src/main/resources/logo.png" alt="Profile API Banner" width="800">
  
  [![Modrinth Downloads](https://img.shields.io/modrinth/dt/profile?style=for-the-badge&logo=modrinth&color=24b473)](https://modrinth.com/mod/profile)
  [![CurseForge Downloads](https://img.shields.io/curseforge/dt/1482084?style=for-the-badge&logo=curseforge&color=f16436)](https://www.curseforge.com/minecraft/mc-mods/profile)
  [![GitHub Issues](https://img.shields.io/github/issues/yigit-guven/Profile-API?style=for-the-badge&logo=github&color=brightgreen)](https://github.com/yigit-guven/Profile-API/issues)
  [![GitHub Stars](https://img.shields.io/github/stars/yigit-guven/Profile-API?style=for-the-badge&logo=github&color=dfb317)](https://github.com/yigit-guven/Profile-API/stargazers)
  [![Discord](https://img.shields.io/discord/123456789012345678?style=for-the-badge&logo=discord&logoColor=white&label=Discord&color=5865F2)](https://discord.gg/gNajXYku5z)
  [![License](https://img.shields.io/badge/License-LGPL_3.0-blue.svg?style=for-the-badge)](https://www.gnu.org/licenses/lgpl-3.0.txt)

  **A highly customizable Profile UI library for Minecraft.**
</div>

---

## 🌟 Overview

**Profile API** is more than just a stats viewer—it's a polished, modular UI framework for Minecraft. By right-clicking any player, you gain access to a beautifully structured profile screen showing real-time character models, equipment, and dynamic data from across the modding ecosystem.

### ✨ Key Features

- 👤 **Real-time 3D Preview**: See players in their full glory with high-resolution, centered 3D model rendering.
- 🛡️ **Interactive Equipment**: View equipped armor slots with hoverable tooltips showing names, enchantments, and stats.
- 📊 **Structured Data**: Information is organized into clean, row-based layouts with subtle highlights.
- 🎨 **Premium Visuals**:
  - **Fancy Panels**: Framed UI elements for a professional, "embedded" feel.
  - **Conditional Coloring**: Health is Red, Experience is Green, and Titles match Team colors.
  - **Dynamic Icons**: Automatic icons for Health, Hunger, and XP (with custom Icon support for developers).
- ⚙️ **Ultra Configurable**: Toggle almost every visual element—from the 3D model to the "Fancy" panel styling.
- 🧩 **Modular API**: Developers can register custom "Profile Components" in seconds.

---

## 🛠️ For Developers

### Adding to your project (Gradle)

```gradle
repositories {
    maven {
        name = "GitHubPackages"
        url = uri("https://maven.pkg.github.com/yigit-guven/Profile-API")
    }
}

dependencies {
    implementation "net.yigitguven.profile:profile:VERSION"
}
```

### Registering a Component

```java
ProfileRegistry.register(player -> new ProfileComponent() {
    @Override
    public ResourceLocation getId() { 
        return new ResourceLocation("yourmod", "example_stat"); 
    }

    @Override
    public Component getTitle() { 
        return Component.literal("Strength"); 
    }

    @Override
    public Component getValue() { 
        // Can be dynamic colored text
        return Component.literal("Lv. 99").withStyle(ChatFormatting.GOLD); 
    }
    
    @Override
    public ResourceLocation getIcon() {
        return new ResourceLocation("yourmod", "textures/gui/strength_icon.png");
    }
});
```

---

## 📦 How 2 Play?

### Installation
1. Download the latest version from [Modrinth](https://modrinth.com/mod/profile) or [CurseForge](https://www.curseforge.com/minecraft/mc-mods/profile).
2. Drop the `.jar` file into your Minecraft `mods` folder.
3. Ensure you have the correct version of **Mod Loader** installed.

### Controls
- **Right-Click** any player to view their profile.
- **Command**: `/profile view <player>` (supports self-view and others).

---

## License & Usage

### Modpacks
You are absolutely free to include **Profile API** in any modpack! No explicit permission is required, though a link back to this page is always appreciated.

### Developing with Profile API
We encourage other developers to use Profile API as a dependency! Since it is licensed under **LGPL 3.0**, you can build mods that depend on this library without having to license your own mod under LGPL, as long as you don't modify the Profile API source code itself.

---

## 🔗 Project Links

- 📚 **[Wiki & Documentation](https://github.com/yigit-guven/Profile-API/wiki)**
- 🐞 **[Issue Tracker](https://github.com/yigit-guven/Profile-API/issues)**
- 📖 **[Modrinth Project](https://modrinth.com/mod/profile)**
- 📖 **[CurseForge Project](https://www.curseforge.com/minecraft/mc-mods/profile)**
- 💬 **[Discord Community](https://discord.gg/gNajXYku5z)**
- 🛠️ **[Source Code](https://github.com/yigit-guven/Profile-API)**

---

## ❤️ Support the Project

If you find this library useful, please consider:
- ⭐ **Starring** the [repository](https://github.com/yigit-guven/Profile-API)!
- 👤 **Following** me on [GitHub](https://github.com/yigit-guven/), [Modrinth](https://modrinth.com/user/yigitguven), and [CurseForge](https://www.curseforge.com/members/yigitguven/projects).
- 🛠️ **Building Mods**: Create new mods that depend on Profile API to grow the ecosystem.
- 🚀 **Server Hosting**: Use our link to buy a server via **BisectHosting** with **25% OFF** using our link: [Get 25% Off Here!](https://url-shortener.curseforge.com/yPgGr)

---

<div align="center">
  Made with ❤️ by <b><a href="https://github.com/yigit-guven/">Yigit Guven</a></b>
</div>
