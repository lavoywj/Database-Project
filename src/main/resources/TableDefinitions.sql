CREATE TABLE SiteMember (
	Username VARCHAR(30),
	Password_Hash CHAR(64),

    PRIMARY KEY(Username)
);

CREATE TABLE Admin (
	Username VARCHAR(30),
    PRIMARY KEY(Username),

	FOREIGN KEY(Username) REFERENCES SiteMember(Username)
);

CREATE TABLE Movie (
    mName VARCHAR(40),
    USReleaseDate DATE,
    intlReleaseDate DATE,
    mDescription TEXT,
    mDirector VARCHAR(35),
    contentRating VARCHAR(10),
    mDuration INT,
    AdminUsername VARCHAR(25),

    PRIMARY KEY(mName, USReleaseDate, intlReleaseDate),
    FOREIGN KEY(AdminUsername) REFERENCES Admin(Username)
        ON DELETE SET NULL
);

CREATE TABLE Actor (
    aName   VARCHAR(40),
    birthDate   DATE,

    PRIMARY KEY(aName, birthDate)
);

CREATE TABLE Acts_In (
    aName   VARCHAR(40),
    aBirthDate  DATE,
    mName   VARCHAR(40),
    mDateUS DATE,
    mDateIntl   DATE,

    PRIMARY KEY(aName, aBirthDate, mName, mDateUS, mDateIntl)
    FOREIGN KEY(mName, mDateUS, mDateIntl) REFERENCES Movie(mName, USReleaseDate, intlReleaseDate)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY(aName, aBirthDate) REFERENCES Actor(aName, birthDate)
        ON UPDATE CASCADE
);

CREATE TABLE Genre (
    gName  VARCHAR(30)
    PRIMARY KEY (gName)
);

CREATE TABLE Describes(
    mName   VARCHAR(40),
    mDateUS DATE,
    mDateIntl   DATE,
    gName   VARCHAR(30),

    PRIMARY KEY(mName, mDateUS, mDateIntl, gName),
    FOREIGN KEY(mName, mDateUS, mDateIntl) REFERENCES Movie(mName, USReleaseDate, intlReleaseDate)
        ON UPDATE CASCADE
        ON DELETE CASCADE,
    FOREIGN KEY(gName) REFERENCES Genre(gName)
        ON UPDATE CASCADE
        ON DELETE CASCADE
);

CREATE TABLE Review (
    rID INT AUTO_INCREMENT,
    MemberUsername VARCHAR(25),
    DateWritten TIMESTAMP,
    mName VARCHAR(40),
    USReleaseDate DATE,
    intlReleaseDate DATE,
    mRating INT,
    rText TEXT,

     PRIMARY KEY(rID),
     FOREIGN KEY(mName, USReleaseDate, intlReleaseDate) REFERENCES Movie(mName, USReleaseDate, intlReleaseDate)
       ON DELETE CASCADE
       ON UPDATE CASCADE,
     FOREIGN KEY(MemberUsername) REFERENCES SiteMember(Username)
);

