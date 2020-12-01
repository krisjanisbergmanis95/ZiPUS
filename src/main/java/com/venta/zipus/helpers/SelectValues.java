package com.venta.zipus.helpers;

import com.venta.zipus.models.publications.pubtypegroups.constants.PublicationTypeGroupTitles;
import com.venta.zipus.models.publications.pubtypes.constants.PublicationTypeTitlesMagazine;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;

public class SelectValues {
    public static final List<String> PAGE_SIZES = new ArrayList<>(Arrays.asList("5", "10", "50", "100", "500"));
    public static final List<String> PUBLICATION_TYPES = new ArrayList<>(Arrays.asList(
            PublicationTypeGroupTitles.GROUP_BOOK,
            PublicationTypeGroupTitles.GROUP_MAGAZINE,
            PublicationTypeGroupTitles.GROUP_CONFERENCE
    ));

    public static final List<String> PUBLICATION_GROUP_TYPE_MAGAZINES = new ArrayList<>(Arrays.asList(
            PublicationTypeTitlesMagazine.PUB_IN_SC_MAGAZINE
    ));
}
