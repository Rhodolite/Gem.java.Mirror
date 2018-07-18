//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Mirror;


import link.crystal.Gem.Core.Gem_Object;
import link.crystal.Gem.Core.Zone;
import link.crystal.Gem.Inspection.Inspection;
import link.crystal.Gem.Interface.Inspectable;


public abstract class   MirrorProxy<PROXY extends MirrorProxy, CLIENT extends Object>
    extends             Gem_Object <Inspection>
//  extends             Object
    implements          Inspectable<Inspection>//,
{
    //
    //  Members
    //
    protected final Zone                z;
    protected final CLIENT              client;


    //
    //  Constructor
    //
    protected                           MirrorProxy(final Zone z, final CLIENT client)
    {
        this.z      = z;
        this.client = client;
    }


    //
    //  Interface Inspectable
    //
    public abstract Inspection          inspect();
}
