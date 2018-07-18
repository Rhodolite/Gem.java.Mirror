//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Mirror;


import java.lang.Object;


public final class  MirrorModule
    extends         Object
{
    //
    //  Public Static members
    //
    public static /*:*/ boolean         startup = true;


    //
    //  Public
    //
    public static final void            initialize()
    {
        MirrorModule.startup = false;

        System.out.println("MirrorModule.initialize");
    }
}
