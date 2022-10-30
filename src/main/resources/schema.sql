DROP TABLE IF EXISTS keyst;
DROP TABLE IF EXISTS interests;
DROP TABLE IF EXISTS users;

CREATE TABLE users (
    userid int AUTO_INCREMENT,
    username varchar(255) NOT NULL,
    emailid varchar(255) NOT NULL UNIQUE,
    Password varchar(255) NOT NULL,
    endpoint varchar(1000),
    primary key(userid)
);

CREATE TABLE interests(
	interestid int,
	interesttitle varchar(255),
    imgurl varchar(1000),
	primary key(interestid)
);

Create TABLE keyst(
  userid int,
  interestid int,
  FOREIGN KEY(userid) REFERENCES users(userid),
  FOREIGN KEY(interestid) REFERENCES interests(interestid),
  primary key(userid, interestid)
);
