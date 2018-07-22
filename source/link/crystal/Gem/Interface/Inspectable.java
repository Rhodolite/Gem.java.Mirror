//  Copyright (c) 2018 Joy Diamond.  All rights reserved.


package link.crystal.Gem.Interface;


import java.lang.String;
import link.crystal.Gem.Core.Gem_StringBuilder;
import link.crystal.Gem.Inspection.Inspection;


public interface    Inspectable<INSPECTION extends Inspection>
{
    //
    //  Interface <me>
    //
    public INSPECTION                   inspect();
    public void                         portray(final Gem_StringBuilder builder);
}
