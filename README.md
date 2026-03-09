<div align="center">
  <img src="src/main/resources/logo.png" alt="Profile API Banner" width="800">
  
  [![Modrinth Downloads](https://img.shields.io/modrinth/dt/profile?style=for-the-badge&logo=modrinth&color=24b473)](https://modrinth.com/mod/profile)
  [![CurseForge Downloads](https://img.shields.io/curseforge/dt/profile-api?style=for-the-badge&logo=curseforge&color=f16436)](https://www.curseforge.com/minecraft/mc-mods/profile)
  [![GitHub Issues](https://img.shields.io/github/issues/yigit-guven/Profile-API?style=for-the-badge&logo=github&color=brightgreen)](https://github.com/yigit-guven/Profile-API/issues)
  [![GitHub Stars](https://img.shields.io/github/stars/yigit-guven/Profile-API?style=for-the-badge&logo=github&color=dfb317)](https://github.com/yigit-guven/Profile-API/stargazers)
  [![Discord](https://img.shields.io/discord/123456789012345678?style=for-the-badge&logo=discord&logoColor=white&label=Discord&color=5865F2)](https://discord.gg/gNajXYku5z)
  [![License](https://img.shields.io/badge/License-LGPL_3.0-blue.svg?style=for-the-badge)](https://www.gnu.org/licenses/lgpl-3.0.txt)

  **A modern library for Minecraft that adds a customizable player profile UI.**
</div>

---

## 🌟 Overview

**Profile API** is a developer-focused library mod that adds a sleek, modern UI accessible via right-clicking on players. It serves as a central hub for other mods to display player-specific data, stats, and achievements in a clean, unified interface.

### ✨ Features
- 🖱️ **Right-Click Interaction**: Seamlessly open profiles by interacting with players.
- 🧩 **Modular API**: Easily register custom cards (Health, Stats, Level, Rank, etc.).
- 🌐 **Network Efficient**: Syncs data only when requested to minimize server load.
- 🎨 **Modern UI**: Clean, minimalist design inspired by modern web portfolios.

---

## 🛠️ For Developers

### Adding to your project (Gradle)

Add the following to your `build.gradle`:

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

Other mods can contribute data to the profile screen using the `ProfileRegistry`:

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
        // Dynamic data based on the player entity
        return Component.literal("Lv. 99"); 
    }
});
```

---

## 📦 How 2 Play?

### Installation
1. Download the latest version from [Modrinth](https://modrinth.com/mod/profile) or [CurseForge](https://modrinth.com/mod/profile).
2. Drop the `.jar` file into your Minecraft `mods` folder.
3. Ensure you have the correct version of **Mod Loader** installed.

### How to use
- **Right-Click** any player to view their profile.
- **Debug Command**: Use `/profile view <player_name>` to view your own or others' profiles via command.

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
