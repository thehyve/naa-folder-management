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


class AmTagItem implements Comparable<AmTagItem> {

    Long id
    String displayName
    Integer displayOrder
//	String tagItemUid
    Integer maxValues
    String guiHandler
    String codeTypeName
    String tagItemType
    String tagItemSubtype
    String tagItemAttr
    Boolean editable = Boolean.TRUE
    Boolean required = Boolean.TRUE
    Boolean activeInd = Boolean.TRUE
    Boolean viewInGrid = Boolean.TRUE
    Boolean viewInChildGrid = Boolean.TRUE
    static belongsTo = [amTagTemplate: AmTagTemplate]

//	AmTagAssociation amTagAssociation

    static mapping = {
        table schema: 'amapp'
        version false
        cache true
        sort "displayOrder"
        amTagTemplate joinTable: [name: 'am_tag_template', key: 'tag_template_id', column: 'tag_item_id'], lazy: false
        id column: 'tag_item_id', generator: 'sequence', params: [sequence: 'AMAPP.SEQ_AMAPP_DATA_ID']
        amTagTemplate column: 'tag_template_id'
//		amTagAssociation joinTable: [name: 'am_tag_association',  key:'tag_item_id', column: 'tag_item_id'], lazy: false

    }

    @Override
    public int compareTo(AmTagItem itemIn) {

        if (itemIn.displayOrder != null && displayOrder != null) {
            displayOrder?.compareTo(itemIn.displayOrder)
        } else if (displayName && itemIn.displayName) {
            displayName.compareTo(itemIn.displayName)
        } else {
            id <=> itemIn.id
        }
    }

    static constraints = {
        tagItemType(maxSize: 200)
        tagItemAttr(maxSize: 200)
        displayName(maxSize: 200)
        codeTypeName(maxSize: 200)
        guiHandler(maxSize: 200)
        tagItemSubtype(nullable: true)
//		tagItemUid(maxSize:300)
    }

    /**
     * override display
     */
    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("ID: ").append(this.id).append(", Display Name: ").append(this.displayName);
        sb.append(", Display Order: ").append(this.displayOrder);
        return sb.toString();
    }

}
