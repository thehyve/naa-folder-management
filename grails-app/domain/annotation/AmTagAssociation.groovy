/*************************************************************************
 * tranSMART - translational medicine data mart
 *
 * Copyright 2008-2012 Janssen Research & Development, LLC.
 *
 * This product includes software developed at Janssen Research & Development, LLC.
 *
 * This program is free software: you can redistribute it and/or modify it under the terms of the GNU General Public License 
 * as published by the Free Software  * Foundation, either version 3 of the License, or (at your option) any later version, along with the following terms:
 * 1.	You may convey a work based on this program in accordance with section 5, provided that you retain the above notices.
 * 2.	You may convey verbatim copies of this program code as you receive it, in any medium, provided that you retain the above notices.
 *
 * This program is distributed in the hope that it will be useful, but WITHOUT ANY WARRANTY; without even the implied warranty of MERCHANTABILITY or FITNESS    * FOR A PARTICULAR PURPOSE.  See the GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 *
 ******************************************************************/


package annotation

class AmTagAssociation implements Serializable {

    String objectType
    String subjectUid
    String objectUid
    Long tagItemId


    static mapping = {
        table schema: 'amapp'
        version false
        cache true
        sort "tagTemplateName"
        id composite: ["objectUid", "subjectUid"]
//		amTagItem column: 'tag_item_id', insert: "false", update: "false"

    }


    static constraints =
            {
            }

    static AmTagAssociation get(String objectUid, Long tagItemId, String subjectUid) {
        find 'from AmTagAssociation where objectUid=:objectUid and tagItemId=:tagItemId and subjectUid=:subjectUid',
                [objectUid: objectUid, tagItemId: tagItemId.toString(), subjectUid: subjectUid]
    }

    static boolean remove(String objectUid, Long tagItemId, String subjectUid, boolean flush = false) {
        AmTagAssociation instance = AmTagAssociation.findByObjectUidAndTagItemIdAndSubjectUid(objectUid, tagItemId.toString(), subjectUid)
        instance ? instance.delete(flush: flush) : false
    }

    /**
     * override display
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("objectType: ").append(this.objectType).append(", subjectUid: ").append(this.subjectUid);
        sb.append(", objectUid: ").append(this.objectUid).append(", tagItemId: ").append(this.tagItemId.toString());
        return sb.toString();
    }


}


