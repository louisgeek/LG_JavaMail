package com.louisgeek.javamail;

import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;

/**
 * Created by louisgeek on 2018/3/18.
 */

public class EmailMessage {
    private Address fromAddress;
    private List<Address> toAddresses;
    private List<Address> ccAddresses;
    private List<Address> bccAddresses;
    private String title;
    private String text;
    private String content;
    private List<File> imageFiles;
    private List<File> files;
    private boolean readReceipt;

    public Address getFromAddress() {
        return fromAddress;
    }

    public List<Address> getToAddresses() {
        return toAddresses;
    }

    public List<Address> getCcAddresses() {
        return ccAddresses;
    }

    public List<Address> getBccAddresses() {
        return bccAddresses;
    }

    public String getTitle() {
        return title;
    }

    public String getText() {
        return text;
    }

    public String getContent() {
        return content;
    }

    public List<File> getImageFiles() {
        return imageFiles;
    }

    public List<File> getFiles() {
        return files;
    }

    public boolean isReadReceipt() {
        return readReceipt;
    }

    private EmailMessage(Builder builder) {
        fromAddress = builder.fromAddress;
        toAddresses = builder.toAddresses;
        ccAddresses = builder.ccAddresses;
        bccAddresses = builder.bccAddresses;
        title = builder.title;
        text = builder.text;
        content = builder.content;
        imageFiles = builder.imageFiles;
        files = builder.files;
        readReceipt = builder.readReceipt;
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static final class Builder {
        private Address fromAddress;
        private List<Address> toAddresses;
        private List<Address> ccAddresses;
        private List<Address> bccAddresses;
        private String title;
        private String text;
        private String content;
        private List<File> imageFiles;
        private List<File> files;
        private boolean readReceipt;

        private Builder() {
        }

        public Builder setFromAddress(Address val) {
            fromAddress = val;
            return this;
        }

        public Builder setFromAddress(String val) {
            try {
                fromAddress = new InternetAddress(val);
            } catch (AddressException e) {
                MyLog.e(e.getMessage());
            }
            return this;
        }

        public Builder setTOAddresses(List<Address> val) {
            toAddresses = val;
            return this;
        }

        public Builder setTOAddresses(Address[] val) {
            if (val != null) {
                toAddresses = new ArrayList<>(Arrays.asList(val));
            }
            return this;
        }

        public Builder addTOAddresses(List<Address> val) {
            if (val != null) {
                if (toAddresses == null) {
                    toAddresses = new ArrayList<>();
                }
                toAddresses.addAll(val);
            }
            return this;
        }

        public Builder addTOAddresses(Address[] val) {
            if (val != null) {
                if (toAddresses == null) {
                    toAddresses = new ArrayList<>();
                }
                List<Address> addresses = new ArrayList<>(Arrays.asList(val));
                toAddresses.addAll(addresses);
            }
            return this;
        }

        public Builder addTOAddress(Address val) {
            if (val != null) {
                if (toAddresses == null) {
                    toAddresses = new ArrayList<>();
                }
                toAddresses.add(val);
            }
            return this;
        }

        public Builder addTOAddress(String val) {
            if (val != null) {
                if (toAddresses == null) {
                    toAddresses = new ArrayList<>();
                }
                try {
                    Address address = new InternetAddress(val);
                    toAddresses.add(address);
                } catch (AddressException e) {
                    MyLog.e(e.getMessage());
                }
            }
            return this;
        }

        ///////////////////////
        public Builder setCCAddresses(List<Address> val) {
            ccAddresses = val;
            return this;
        }

        public Builder setCCAddresses(Address[] val) {
            if (val != null) {
                ccAddresses = new ArrayList<>(Arrays.asList(val));
            }
            return this;
        }

        public Builder addCCAddresses(List<Address> val) {
            if (val != null) {
                if (ccAddresses == null) {
                    ccAddresses = new ArrayList<>();
                }
                ccAddresses.addAll(val);
            }
            return this;
        }

        public Builder addCCAddresses(Address[] val) {
            if (val != null) {
                if (ccAddresses == null) {
                    ccAddresses = new ArrayList<>();
                }
                List<Address> addresses = new ArrayList<>(Arrays.asList(val));
                ccAddresses.addAll(addresses);
            }
            return this;
        }

        public Builder addCCAddress(Address val) {
            if (val != null) {
                if (ccAddresses == null) {
                    ccAddresses = new ArrayList<>();
                }
                ccAddresses.add(val);
            }
            return this;
        }

        public Builder addCCAddress(String val) {
            if (val != null) {
                if (ccAddresses == null) {
                    ccAddresses = new ArrayList<>();
                }
                try {
                    Address address = new InternetAddress(val);
                    ccAddresses.add(address);
                } catch (AddressException e) {
                    MyLog.e(e.getMessage());
                }
            }
            return this;
        }

        //////////////////////
        public Builder setBCCAddresses(List<Address> val) {
            bccAddresses = val;
            return this;
        }

        public Builder setBCCAddresses(Address[] val) {
            if (val != null) {
                bccAddresses = new ArrayList<>(Arrays.asList(val));
            }
            return this;
        }

        public Builder addBCCAddresses(List<Address> val) {
            if (val != null) {
                if (bccAddresses == null) {
                    bccAddresses = new ArrayList<>();
                }
                bccAddresses.addAll(val);
            }
            return this;
        }

        public Builder addBCCAddresses(Address[] val) {
            if (val != null) {
                if (bccAddresses == null) {
                    bccAddresses = new ArrayList<>();
                }
                List<Address> addresses = new ArrayList<>(Arrays.asList(val));
                bccAddresses.addAll(addresses);
            }
            return this;
        }

        public Builder addBCCAddress(Address val) {
            if (val != null) {
                if (bccAddresses == null) {
                    bccAddresses = new ArrayList<>();
                }
                bccAddresses.add(val);
            }
            return this;
        }

        public Builder addBCCAddress(String val) {
            if (val != null) {
                if (bccAddresses == null) {
                    bccAddresses = new ArrayList<>();
                }
                try {
                    Address address = new InternetAddress(val);
                    bccAddresses.add(address);
                } catch (AddressException e) {
                    MyLog.e(e.getMessage());
                }
            }
            return this;
        }

        ///
        public Builder setTitle(String val) {
            title = val;
            return this;
        }

        public Builder setText(String val) {
            text = val;
            return this;
        }

        public Builder setContent(String val) {
            content = val;
            return this;
        }

        public Builder setImageFiles(List<File> val) {
            imageFiles = val;
            return this;
        }

        public Builder setImageFiles(File[] val) {
            if (val != null) {
                imageFiles = new ArrayList<>(Arrays.asList(val));
            }
            return this;
        }

        public Builder addImageFiles(List<File> val) {
            if (val != null) {
                if (imageFiles == null) {
                    imageFiles = new ArrayList<>();
                }
                imageFiles.addAll(val);
            }
            return this;
        }

        public Builder addImageFiles(File[] val) {
            if (val != null) {
                if (imageFiles == null) {
                    imageFiles = new ArrayList<>();
                }
                List<File> addresses = new ArrayList<>(Arrays.asList(val));
                imageFiles.addAll(addresses);
            }
            return this;
        }

        public Builder addImageFile(File val) {
            if (val != null) {
                if (imageFiles == null) {
                    imageFiles = new ArrayList<>();
                }
                imageFiles.add(val);
            }
            return this;
        }


        ///////
        public Builder setFiles(List<File> val) {
            files = val;
            return this;
        }

        public Builder setFiles(File[] val) {
            if (val != null) {
                files = new ArrayList<>(Arrays.asList(val));
            }
            return this;
        }

        public Builder addFiles(List<File> val) {
            if (val != null) {
                if (files == null) {
                    files = new ArrayList<>();
                }
                files.addAll(val);
            }
            return this;
        }

        public Builder addFiles(File[] val) {
            if (val != null) {
                if (files == null) {
                    files = new ArrayList<>();
                }
                List<File> addresses = new ArrayList<>(Arrays.asList(val));
                files.addAll(addresses);
            }
            return this;
        }

        public Builder addFile(File val) {
            if (val != null) {
                if (files == null) {
                    files = new ArrayList<>();
                }
                files.add(val);
            }
            return this;
        }


        public Builder setReadReceipt(boolean val) {
            readReceipt = val;
            return this;
        }

        public EmailMessage build() {
            return new EmailMessage(this);
        }
    }
}
