package server.database;

import java.sql.*;
import java.util.HashMap;

/**
 * This class is made using Singleton pattern. It is done because, we always need only one instance of the pf DatabaseAccess class
 */
public class DataBaseAccess {
    private static DataBaseAccess dataBaseAccess;

    /**
     * Getter of the connection
     * Also, closes unnecessary statements and connection
     *
     * @return connection to the database
     */
    public synchronized Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:sqlite:BondInBand.db");
        } catch (SQLException e) {
            throw new RuntimeException("Failed to get DB connection!");
        }
    }

    /**
     * 15 line - Driver for sqlLite is downloaded
     * 16 line - Connection to certain database. If database with such name doesn't exist = it will be created.
     */
    private DataBaseAccess() {
        try {
            Class.forName("org.sqlite.JDBC");
            createTables();
            insertIntoDefaultData();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }

    }


    /**
     * In the beginning the tables are created.
     * Also, if the table with regions wasn't filled - it will be filled with predetermined cities and regions
     */
    private void createTables() {
        try (Connection con = getConnection(); Statement statement = con.createStatement())
        {
            createRegionsTable(statement);
            createTableCities(statement);
            createUsersTable(statement);
            createOrganizersTable(statement);
            createMusiciansTable(statement);
            createInstrumentsTable(statement);
            createMusicianInstrumentsTable(statement);
            createGenresTable(statement);
            createBandsTable(statement);
            createBandMembersTable(statement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }

    }

    private void insertIntoDefaultData() {
        try (Connection con = getConnection(); Statement statement = con.createStatement()) {
            insertDefaultRegions(statement);
            insertIntoDefaultCities(statement);
            insertDefaultInstruments(statement);
            insertDefaultGenres(statement);
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
    }


    private void insertDefaultRegions(Statement statement) throws SQLException
    {
            statement.executeUpdate("INSERT OR IGNORE  INTO regions(regionId,regionName) values (1,'Nordjylland'),(2,'Midtjylland'), (3, 'Syddanmark'), (4, 'Sjælland'), (5,'Hovedstaden')");
    }

private void createUsersTable(Statement statement) throws SQLException {
    statement.executeUpdate("CREATE TABLE IF NOT EXISTS users(id INTEGER PRIMARY KEY AUTOINCREMENT ,nickname varchar(60) unique not null, password varchar(60) not null, dateOfBirth date not null, city varchar(60) not null, FOREIGN KEY(city) REFERENCES cities(cityName))");
}
    private void insertIntoDefaultCities(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT OR IGNORE into  cities (cityName, regionId) values ('Aalborg', 1),('Hjørring', 1), ('Frederikshavn', 1),('Thisted', 1), ('Brønderslev', 1), ('Hobro', 1), ('Nykøbing Mors',1 ), ('Sæby', 1), ('Skagen', 1), ('Aars',1), ('Støvring', 1), ('Svenstrup', 1), ('Hirtshals',1), ('Aabybro', 1), ('Nibe',1 ), ('Hadsund', 1) , ('Allingabro', 2), ('Arhus', 2), ('Assentoft',  2), ('Auning', 2), ('Avlum', 2),('Beder', 2), ('Bjerringbro', 2), ('Bording Kirkeby', 2), ('Braedstrup', 2),('Brande', 2),('Ebeltoft', 2), ('Framlev', 2), ('Galten', 2), ('Gjellerup Kirkeby', 2), ('Grenaa', 2), ('Hadsten', 2),('Hammel',2), ('Hammerum', 2), ('Harboore', 2), ('Hedensted', 2), ('Herning', 2), ('Hinnerup', 2), ('Hjortshoj', 2), ('Hojslev', 2), ('Holstebro', 2), ('Horning', 2), ('Hornslet', 2), ('Horsens', 2), ('Hvide Sande', 2), ('Ikast',2), ('Juelsminde', 2), ('Karup', 2), ('Kibaek', 2),('Kjellerup', 2), ('Kolt', 2), ('Langa', 2), ('Lemvig',2 ), ('Lind',2),('Logten', 2), ('Lystrup', 2), ('Malling', 2), ('Marslet', 2), ('Odder', 2),('Randers', 2), ('Ringkobing', 2), ('Ronde', 2), ('Ry',2), ('Ryomgard', 2), ('Sabro', 2), ('Silkeborg', 2), ('Skanderborg', 2), ('Skive',2), ('Skjern',2), ('Skovby', 2),('Snejbjerg', 2), ('Soften', 2), ('Solbjerg', 2), ('Spentrup', 2),('Stavtrup', 2), ('Stilling', 2), ('Stoholm', 2), ('Struer', 2), ('Sunds', 2), ('Svejbaek', 2), ('Tarm', 2),('Thyboron', 2), ('Torring', 2), ('Tranbjerg', 2), ('Tranebjerg', 2),('Trige', 2), ('Ulfborg', 2), ('Ulstrup', 2), ('Viborg', 2), ('Videbaek', 2), ('Vildbjerg', 2), ('Vinderup', 2),('Virklund', 2), ('Aabenraa', 3), ('Årslev',3), ('Aarup', 3), ('Ærøskøbing', 3), ('Andkær', 3),('Årre',3 ), ('Assens',3 ), ('Augustenborg',3), ('Aunslev', 3), ('Bække',3), ('Båring',3), ('Billund', 3), ('Birkende', 3), ('Bogense',3 ),('Brændstrup', 3), ('Bramming Municipality', 3), ('Bredebro',3), ('Bredsten', 3), ('Bregninge, Svendborg Municipality', 3), ('Brenderup', 3), ('Broager', 3), ('Brørup',3 ), ('Christiansfeld', 3), ('Døstrup', 3), ('Dybbøl', 3), ('Egtved', 3), ('Ejby, Middelfart Municipality', 3), ('Ellinge', 3), ('Esbjerg', 3), ('Faaborg', 3), ('Fredericia', 3), ('Frørup', 3), ('Gelsted', 3 ), ('Give', 3), ('Givskud', 3), ('Glamsbjerg', 3), ('Gram', 3), ('Gråsten', 3), ('Grindsted', 3),('Gudbjerg', 3), ('Guderup',3), ('Gudme', 3), ('Haarby',3 ), ('Haderslev',3), ('Harndrup', 3), ('Havneby',3), ('Hjordkær', 3), ('Ho', 3),('Højer',3), ('Holsted', 3), ('Høruphav',3), ('Jelling', 3), ('Kerteminde', 3), ('Kirkeby',3), ('Kolding', 3), ('Kruså', 3), ('Kvong',3 ), ('Langeskov',3), ('Løgumkloster',3), ('Lundby, Svendborg Municipality',3 ), ('Lundeborg',3), ('Marstal',3), ('Middelfart', 3), ('Møgeltønder', 3), ('Munkebo', 3), ('Nordborg', 3), ('Nordby', 3), ('Nørre Aaby', 3), ('Nørre Nebel', 3), ('Nyborg', 3), ('Odense', 3), ('Oksbøl', 3), ('Ølgod', 3), ('Ollerup', 3), ('Ørbæk', 3), ('Otterup', 3), ('Outrup', 3),('Over Holluf', 3), ('Padborg',3), ('Rantzausminde', 3), ('Ribe', 3), ('Ringe', 3), ('Rødding', 3), ('Rødekro', 3),('Rudkøbing', 3), ('Ryslinge', 3), ('Særslev', 3), ('Skærbæk', 3), ('Skamby', 3),('Skårup', 3), ('Snoghøj', 3), ('Sønder Bjert', 3), ('Sønderborg', 3), ('Sønderho',3), ('Søndersø', 3), ('Stenstrup', 3), ('Strammelse', 3), ('Strib', 3), ('Svendborg', 3), ('Taulov', 3), ('Thurø By', 3), ('Tinglev', 3), ('Tistrup', 3), ('Tjæreborg', 3), ('Tommerup', 3), ('Tommerup Stationsby', 3), ('Tønder', 3), ('Troense', 3), ('Uggerslev', 3), ('Ulbølle', 3), ('Ullerslev', 3), ('Vamdrup', 3), ('Varde', 3),('Vejen', 3), ('Vejle', 3), ('Vester Skerninge', 3), ('Vindeby, Svendborg Municipality', 3), ('Vissenbjerg', 3), ('Vojens', 3), ('Vorbasse', 3),('Algestrup', 4),('Asnæs', 4), ('Bandholm', 4), ('Bjæverskov', 4), ('Borre', 4), ('Borup, Køge Municipality', 4), ('Dalby, Faxe Municipality', 4), ('Dannemare', 4), ('Ejby, Køge Municipality', 4), ('Ejby, Lejre Municipality', 4), ('Eskilstrup', 4), ('Faxe', 4), ('Faxe Ladeplads', 4), ('Fuglebjerg', 4), ('Gadstrup', 4), ('Gedser', 4), ('Gevninge', 4), ('Glumsø', 4), ('Gørlev', 4), ('Greve Strand', 4), ('Guldborg',4 ), ('Hårlev', 4), ('Haslev', 4), ('Hasselø Plantage', 4), ('Havdrup', 4), ('Havrebjerg', 4), ('Højby', 4), ('Holbæk', 4), ('Holeby', 4), ('Holeby Municipality', 4), ('Høng', 4), ('Horbelev', 4), ('Horreby', 4), ('Horslunde', 4), ('Hunseby', 4), ('Hvalsø', 4), ('Idestrup', 4), ('Jyderup', 4), ('Jyllinge', 4), ('Jystrup', 4), ('Kalundborg', 4), ('Karise', 4), ('Kettinge', 4), ('Knabstrup', 4), ('Køge', 4), ('Køng', 4), ('Korsør', 4), ('Kundby', 4),('Langebæk', 4), ('Lejre', 4), ('Lille Skensved', 4), ('Lundby, Vordingborg Municipality', 4), ('Maribo', 4), ('Marielyst', 4), ('Mern', 4), ('Næstved', 4), ('Nagelsti', 4), ('Nakskov', 4), ('Nordbyen', 4), ('Nørre Alslev', 4), ('Nørreballe', 4), ('Nykøbing Falster', 4), ('Nykøbing Sjælland', 4), ('Nykøbing Strandhuse', 4), ('Nyråd', 4), ('Nysted', 4), ('Øm', 4), ('Ønslev', 4), ('Orehoved', 4), ('Ortved', 4), ('Osted', 4), ('Øster Kippinge', 4), ('Øster Ulslev', 4), ('Præstø', 4), ('Reersø', 4), ('Ringsted', 4), ('Rødby', 4), ('Rødbyhavn', 4), ('Rønnede', 4), ('Rørvig', 4), ('Roskilde', 4), ('Ruds Vedby', 4),('Sakskøbing', 4), ('Sandby', 4), ('Skælskør', 4), ('Slagelse',4), ('Søllested', 4), ('Solrød Strand', 4), ('Sønder Vedby Skovhuse', 4), ('Sorø', 4), ('Stege', 4), ('Stensved', 4), ('Stokkemarke', 4), ('Store Damme', 4), ('Store Heddinge', 4), ('Store Merløse', 4), ('Stubbekøbing', 4), ('Sundby (Lolland)', 4), ('Svinninge, Holbæk Municipality', 4), ('Svogerslev', 4), ('Systofte Skovby', 4), ('Tingsted', 4), ('Tølløse', 4), ('Toreby', 4), ('Tune', 4), ('Undløse', 4), ('Væggerløse', 4), ('Vedde', 4), ('Viby, Roskilde Municipality', 4), ('Vordingborg', 4), ('Aakirkeby', 5), ('Aarsballe', 5), ('Aarsdale', 5), ('Albertslund', 5), ('Allinge-Sandvig', 5), ('Arnager', 5), ('Bagsværd', 5), ('Balka', 5), ('Ballerup', 5), ('Birkerød', 5), ('Boderne', 5), ('Brøndbyøster', 5), ('Brøndbyvester', 5), ('Charlottenlund', 5), ('Copenhagen', 5), ('Dragør', 5), ('Dronningmølle', 5), ('Espergærde', 5), ('Farum', 5), ('Fløng', 5), ('Fredensborg', 5), ('Frederiksberg', 5), ('Frederikssund', 5), ('Frederiksværk', 5), ('Ganløse', 5), ('Gilleleje', 5), ('Glostrup', 5), ('Græse Bakkeby', 5), ('Græsted', 5), ('Gudhjem', 5), ('Hasle', 5), ('Hedehusene', 5), ('Hellebæk', 5), ('Helsinge',5), ('Helsingør', 5), ('Herlev',5), ('Hillerød', 5), ('Hjortekær', 5), ('Holte', 5), ('Hornbæk', 5), ('Hørsholm', 5), ('Humlebæk', 5), ('Hundested', 5), ('Hvidovre', 5), ('Ishøj', 5), ('Jægerspris', 5), ('Kastrup', 5), ('Kirke Værløse', 5), ('Klemensker', 5), ('Kongens Lyngby', 5), ('Laanshøj', 5), ('Ledøje', 5), ('Lillerød', 5), ('Liseleje', 5), ('Listed', 5), ('Lobbæk', 5), ('Munkerup', 5), ('Nærum', 5), ('Nexø', 5), ('Nivå', 5), ('Nyker', 5), ('Nylars', 5), ('Ølsted',5), ('Ølstykke', 5), ('Ølstykke-Stenløse', 5), ('Østerlars', 5), ('Østermarie', 5), ('Rødovre', 5), ('Rønne', 5), ('Skævinge', 5), ('Skibby', 5), ('Skodsborg', 5), ('Slangerup', 5), ('Smørumnedre', 5), ('Snekkersten', 5), ('Snogebæk', 5), ('Søllerød', 5), ('Sorthat-Muleby', 5), ('Søsum', 5), ('Stenløse', 5), ('Store Magleby', 5), ('Svaneke', 5), ('Taastrup', 5), ('Tårnby', 5), ('Tejn', 5), ('Tisvilde', 5), ('Værløse', 5), ('Vallensbæk', 5), ('Vallensbæk Landsby', 5), ('Vedbæk',5), ('Veksø', 5), ('Vestermarie', 5), ('Virum', 5)");
    }

    private void createTableCities(Statement statement) throws SQLException {
        statement.executeUpdate("Create table if not exists cities(cityId INTEGER PRIMARY KEY AUTOINCREMENT, cityName varchar(120) not null unique, regionId INTEGER NOT NULL, FOREIGN KEY (regionId) REFERENCES regions(regionId))");
    }

    private void insertDefaultInstruments(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT OR IGNORE INTO instruments (instrumentName) values ('Guitar'), ('Piano'), ('Vocal'), ('Bass'), ('Drums'), ('Dj')");
    }

    private void insertDefaultGenres(Statement statement) throws SQLException {
        statement.executeUpdate("INSERT OR IGNORE INTO genres (genre) values ('Alternative'), ('Blues'), ('Classical'), ('Country'), ('Electronic'), ('Hip-Hop/Rap'), ('Instrumental'), ('Jazz'), ('Latin'), ('Pop'), ('Reggae'), ('Rock'), ('Vocal')");
    }

    private void createMusicianInstrumentsTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS musicianInstruments (id INTEGER PRIMARY KEY AUTOINCREMENT, musicianId INTEGER NOT NULL, instrumentId INTEGER NOT NULL , expertise INTEGER NOT NULL, FOREIGN KEY (musicianId) REFERENCES musicians(userid), FOREIGN KEY (instrumentid) REFERENCES instruments(instrumentId), UNIQUE(musicianId, instrumentId))");
    }

    private void createInstrumentsTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS instruments (instrumentId INTEGER PRIMARY KEY AUTOINCREMENT, instrumentName varchar(60) unique not null, instrumentIcon varchar (255) )");
    }

    private void createMusiciansTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS musicians (musicianUserId INTEGER PRIMARY KEY, nickname varchar(60) not null , bio varchar(400), FOREIGN KEY (musicianUserId) REFERENCES  users(id))");
    }

    private void createBandsTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS bands (bandId INTEGER PRIMARY KEY AUTOINCREMENT, bandName varchar(60) not null UNIQUE, foundationDate date, cityId INTEGER not null, genreId INTEGER not null, bio varchar(400), email varchar(120), phoneNr char(8), bandAdministratorId INTEGER NOT NULL, bandLogo BLOB, FOREIGN KEY (genreId) REFERENCES genres(id), FOREIGN KEY (cityId) REFERENCES  cities(cityId), FOREIGN KEY (bandAdministratorId) REFERENCES band(bandId))");
    }

    private void createBandMembersTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS bandMembers (bandMemberId INTEGER PRIMARY KEY AUTOINCREMENT, bandId INTEGER not null, musicianId INTEGER, FOREIGN KEY(bandId) REFERENCES bands(bandId), FOREIGN KEY(musicianId) REFERENCES musicians(musicianUserId), UNIQUE(bandId, musicianId));");
    }

    private void createOrganizersTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS organizers(organizerUserId INTEGER PRIMARY KEY, fullName varchar(60) not null, bio varchar(400), email TEXT, telNo TEXT, FOREIGN KEY(organizerUserId) REFERENCES  users(id))");
    }



    private void createRegionsTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS regions(regionId INTEGER PRIMARY KEY, regionName varchar (60))");
    }

    private void createGenresTable(Statement statement) throws SQLException {
        statement.executeUpdate("CREATE TABLE IF NOT EXISTS genres (id INTEGER PRIMARY KEY AUTOINCREMENT, genre VARCHAR (60) NOT NULL UNIQUE)");
    }

    /**
     * This method checks if Database was already created. If it wasn't new database is created, otherwise already creted instance is returned. It is useful, because we will not create a lot of unused objects with the same functionality
     *
     * @return this database
     */
    public synchronized static DataBaseAccess getInstance() {
        if (dataBaseAccess == null) {
            dataBaseAccess = new DataBaseAccess();
        }
        return dataBaseAccess;
    }


    public HashMap<Integer, String> fetchInstrumentsId() {
        try (Connection con = getConnection(); Statement statement = con.createStatement()) {
            ResultSet resultSet = statement.executeQuery("SELECT * from instruments;");
            HashMap<Integer, String> result = new HashMap<>();
            while(resultSet.next()){
                result.put(resultSet.getInt("instrumentId"), resultSet.getString("instrumentName"));
            }
            return result;
        } catch (SQLException sqlException) {
            sqlException.printStackTrace();
        }
        return null;
    }
}
