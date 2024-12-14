package com.mulmeong.member.auth.util;

import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Random;

@Component
public class RandomProfileImageUtil {

    private static final String PREFIX = "image/";

    // 랜덤 이미지 선택 메서드
    public String getRandomProfileImage() {
        int randomIndex = RANDOM.nextInt(PROFILE_IMAGES.size());
        return PREFIX + PROFILE_IMAGES.get(randomIndex);
    }


    private static final List<String> PROFILE_IMAGES = List.of(
            "001-lamprey.svg",
            "001-redfish.svg",
            "002-hagfish.svg",
            "002-tilapia.svg",
            "003-chimaera.svg",
            "003-skate fish.svg",
            "004-ray.svg",
            "004-snapper.svg",
            "005-anabas.svg",
            "005-eel.svg",
            "006-eel.svg",
            "006-reedfish.svg",
            "007-catfish.svg",
            "007-lungfish.svg",
            "008-ocellaris clownfish.svg",
            "008-sunfish.svg",
            "009-catfish.svg",
            "009-pterophylium salare.svg",
            "010-blenny.svg",
            "010-goldfish.svg",
            "011-sucker.svg",
            "011-zebrafish.svg",
            "012-guppy.svg",
            "012-red firefish goby.svg",
            "013-royal dottyback.svg",
            "013-siamese fighting fish.svg",
            "014-guppy.svg",
            "014-swordfish.svg",
            "015-blue tang.svg",
            "015-seahorse.svg",
            "016-bluegill.svg",
            "016-scat fish.svg",
            "017-grouper.svg",
            "017-yellow tang.svg",
            "018-butterflyfish.svg",
            "018-red drum fish.svg",
            "019-european perch.svg",
            "019-moorish idol.svg",
            "020-neon tetra.svg",
            "020-scalar.svg",
            "021-grouper.svg",
            "021-tarpon.svg",
            "022-blue mao mao.svg",
            "022-sturgeon.svg",
            "023-goldfish.svg",
            "023-kissing fish.svg",
            "024-black moor.svg",
            "024-butterflyfish.svg",
            "025-asian arowana.svg",
            "026-atlantic pomfret.svg",
            "026-carp.svg",
            "027-cod.svg",
            "027-shark.svg",
            "028-trout.svg",
            "028-tuna.svg",
            "029-gilt head bream.svg",
            "029-salmon.svg",
            "030-bass.svg",
            "030-goby fish.svg",
            "031-barreleye fish.svg",
            "031-pike.svg",
            "032-blind cave fish.svg",
            "032-sawfish.svg",
            "033-neon tetras.svg",
            "034-puffer fish.svg",
            "034-unicorn fish.svg",
            "035-cowfish.svg",
            "035-zander.svg",
            "036-boxfish.svg",
            "036-oscar fish.svg",
            "037-european seabass.svg",
            "037-pufferfish.svg",
            "038-anglefish.svg",
            "038-tench.svg",
            "039-atlantic cod.svg",
            "039-piranha.svg",
            "040-atlantic mackerel.svg",
            "040-royal flagfin.svg",
            "041-blue fish.svg",
            "041-flying fish.svg",
            "042-anchovy.svg",
            "042-ocean sunfish.svg",
            "043-rainbow trout.svg",
            "043-sardine.svg",
            "044-basa fish.svg",
            "044-flounder.svg",
            "045-bream.svg",
            "045-tonguefish.svg",
            "046-beardfish.svg",
            "046-doctor fish.svg",
            "047-redfish.svg",
            "047-stand steenbras.svg",
            "048-nile tilapla.svg",
            "048-redfish.svg",
            "049-dory.svg",
            "049-grey mullet.svg",
            "050-bloodfish tetras.svg",
            "050-tilapia.svg"
    );

    private static final Random RANDOM = new Random();


}