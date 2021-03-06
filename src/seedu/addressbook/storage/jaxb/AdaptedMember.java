package seedu.addressbook.storage.jaxb;

import seedu.addressbook.common.Utils;
import seedu.addressbook.data.exception.IllegalValueException;
import seedu.addressbook.data.member.Member;
import seedu.addressbook.data.member.Points;
import seedu.addressbook.data.member.ReadOnlyMember;
import seedu.addressbook.data.person.*;
import seedu.addressbook.data.tag.Tag;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlValue;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * JAXB-friendly adapted person data holder class.
 */
public class AdaptedMember {

    private static class AdaptedContactDetail {
        @XmlValue
        public String value;
        @XmlAttribute(required = true)
        public boolean isPrivate;
    }

    @XmlElement(required = true)
    private String name;

    @XmlElement(required = true)
    private String points;
//    @XmlElement(required = true)
//    private AdaptedContactDetail phone;
//    @XmlElement(required = true)
//    private AdaptedContactDetail email;
//    @XmlElement(required = true)
//    private AdaptedContactDetail address;
//
//    @XmlElement
//    private List<AdaptedTag> tagged = new ArrayList<>();

    /**
     * No-arg constructor for JAXB use.
     */
    public AdaptedMember() {}


    /**
     * Converts a given Person into this class for JAXB use.
     *
     * @param source future changes to this will not affect the created AdaptedPerson
     */
    public AdaptedMember(ReadOnlyMember source) {
        name = source.getName().fullName;
        points = source.getPoints().value;

//        phone = new AdaptedContactDetail();
//        phone.isPrivate = source.getPhone().isPrivate();
//        phone.value = source.getPhone().value;
//
//        email = new AdaptedContactDetail();
//        email.isPrivate = source.getEmail().isPrivate();
//        email.value = source.getEmail().value;
//
//        address = new AdaptedContactDetail();
//        address.isPrivate = source.getAddress().isPrivate();
//        address.value = source.getAddress().value;
//
//        tagged = new ArrayList<>();
//        for (Tag tag : source.getTags()) {
//            tagged.add(new AdaptedTag(tag));
//        }
    }

    /**
     * Returns true if any required field is missing.
     *
     * JAXB does not enforce (required = true) without a given XML schema.
     * Since we do most of our validation using the data class constructors, the only extra logic we need
     * is to ensure that every xml element in the document is present. JAXB sets missing elements as null,
     * so we check for that.
     */
    public boolean isAnyRequiredFieldMissing() {
//        for (AdaptedTag tag : tagged) {
//            if (tag.isAnyRequiredFieldMissing()) {
//                return true;
//            }
//        }
        // second call only happens if phone/email/address are all not null
        return Utils.isAnyNull(name);
//                || Utils.isAnyNull(phone.value, email.value, address.value);
    }

    /**
     * Converts this jaxb-friendly adapted person object into the Person object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person
     */
    public Member toModelType() throws IllegalValueException {
//        final Set<Tag> tags = new HashSet<>();
//        for (AdaptedTag tag : tagged) {
//            tags.add(tag.toModelType());
//        }
        final Name name = new Name(this.name);
        final Points points = new Points();
//        final Phone phone = new Phone(this.phone.value, this.phone.isPrivate);
//        final Email email = new Email(this.email.value, this.email.isPrivate);
//        final Address address = new Address(this.address.value, this.address.isPrivate);
        return new Member(name, points);
    }
}
