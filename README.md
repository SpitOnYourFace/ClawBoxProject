<p align="center">
  <img src="logo.png" alt="ClawBox Logo" width="120">
</p>

<h1 align="center">ClawBox</h1>

<p align="center">
  <strong>Component Inventory Management System</strong><br>
  A Java Swing desktop application for managing electronic components, suppliers, clients, and orders — built with H2 embedded database.
</p>

<p align="center">
  <img src="https://img.shields.io/badge/Java-8%2B-orange?logo=openjdk" alt="Java 8+">
  <img src="https://img.shields.io/badge/Database-H2-blue" alt="H2 Database">
  <img src="https://img.shields.io/badge/GUI-Swing-green" alt="Swing GUI">
</p>

---

## About

ClawBox is a CRUD (Create, Read, Update, Delete) desktop application designed to manage the inventory and ordering pipeline for electronic components used in robotics projects. It provides a tabbed interface for managing:

- **Components** (Компоненти) — electronic parts with name, category, supplier, description, quantity, and price
- **Categories** (Категории) — component categories (e.g., Nvidia Jetson Nano, Hard Disc, Cases)
- **Suppliers** (Доставчици) — supplier companies with name and address
- **Clients** (Клиенти) — customers with first name, last name, and phone number
- **Orders** (Поръчки) — orders linking components to clients with quantity and dates
- **Search** (Справка) — filtered search by category, supplier, and max price

## Project Structure

```
ClawBoxProject/
├── src/
│   ├── MainClass.java          # Application entry point
│   ├── ClawBoxFrame.java       # Main JFrame with all tabs and CRUD logic
│   ├── MyDBConnection.java     # H2 database connection and schema initialization
│   └── MyModel.java            # Custom AbstractTableModel for JTable display
├── driver/
│   └── h2-2.2.224.jar          # H2 Database Engine (embedded)
├── build.bat                   # Windows build & run script
├── build.sh                    # Linux/macOS build & run script
├── logo.png                    # Application logo
├── .gitignore
└── README.md
```

## Prerequisites

- **Java 8** or higher (JDK, not just JRE — needed for `javac`)
- No other dependencies — H2 driver is included in `driver/`

## Quick Start

### Windows

```cmd
build.bat
```

### Linux / macOS

```bash
chmod +x build.sh
./build.sh
```

Both scripts will:
1. Create the `DB/` directory for the H2 database files
2. Compile all Java sources with UTF-8 encoding
3. Launch the application

## Database Schema

The application uses an **H2 embedded database** that auto-initializes on first run. The schema consists of 5 tables:

```
┌────────────┐     ┌──────────────┐     ┌─────────────┐
│ KATEGORII  │     │  KOMPONENTI  │     │ DOSTAVCHICI │
├────────────┤     ├──────────────┤     ├─────────────┤
│ ID (PK)    │◄────│ IDKATEGORIYA │     │ ID (PK)     │
│ NAME       │     │ IDDOSTAVCHIK │────►│ NAME        │
└────────────┘     │ ID (PK)      │     │ ADRES       │
                   │ NAME         │     └─────────────┘
                   │ OPIS         │
                   │ KOL          │
                   │ CENA         │
                   └──────┬───────┘
                          │
                   ┌──────┴───────┐
                   │   PORUCHKI   │
                   ├──────────────┤
                   │ ID (PK)      │
                   │ IDKOMPONENT  │────► KOMPONENTI.ID
                   │ IDKLIENT     │────► KLIENTI.ID
                   │ KOL          │
                   │ DATAPR       │
                   │ DATAIZ       │
                   └──────────────┘
                          │
                   ┌──────┴───────┐
                   │   KLIENTI    │
                   ├──────────────┤
                   │ ID (PK)      │
                   │ FNAME        │
                   │ LNAME        │
                   │ TELEFON      │
                   └──────────────┘
```

**Initial seed data** is inserted automatically when the database is first created:
- 3 categories: Nvidia Jetson Nano, 512GB Hard Disc, Cases for the box
- 3 suppliers: AliExpress (China), Mouser (USA), Farnell (UK)

## Architecture

### Design Pattern
The application follows a simple **MVC-like** pattern within a single-frame Swing application:

- **Model**: `MyModel` (custom `AbstractTableModel`) bridges JDBC `ResultSet` data to `JTable` display. Column names are cached at construction time for safe resource cleanup.
- **View**: `ClawBoxFrame` builds the UI with `JTabbedPane`, forms (`JTextField`, `JComboBox`), buttons, and tables.
- **Controller**: Inner `ActionListener` classes (`AddKomponentDB`, `DeleteKomponentDB`, etc.) handle user actions.

### Key Technical Details

- **Database connections** are reused via `MyDBConnection.getConnection()` which checks `conn.isClosed()` before opening a new connection
- **JDBC resources** use `try-with-resources` for all `PreparedStatement` and `ResultSet` instances to prevent cursor corruption and resource leaks
- **Input validation** uses `JOptionPane` dialogs for numeric fields (quantity, price) and date fields (YYYY-MM-DD format)
- **Referential integrity** is enforced by the database — delete operations show user-friendly error messages when FK constraints are violated
- **Combo box queries** use `ORDER BY ID` for deterministic ordering
- **Table joins** use explicit `LEFT JOIN` syntax to handle potential NULL foreign keys

## Usage

1. **Run** the application using `build.bat` (Windows) or `build.sh` (Linux/macOS)
2. **Navigate** between tabs to manage different entities
3. **Add** records by filling in the form fields and clicking "Добави"
4. **Edit** records by clicking a table row (populates the form), modifying fields, then clicking "Промени"
5. **Delete** records by clicking a table row and clicking "Изтрий"
6. **Search** in the Справка tab by selecting category, supplier, and optionally entering a max price

## License

This project is part of an educational exam project for component inventory management.

---

<p align="center">
  Built by <a href="https://github.com/SpitOnYourFace">ID Robots</a>
</p>
