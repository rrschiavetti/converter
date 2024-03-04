## Assumptions
- Once it is a library, it is assumed that the user is using it since v 0.0.1, so I chose to not create a new version for each task and keep the same main version.
- The flags are not mandatory to keep the functionality working since the version (task) one without breaking. The user can still use it even after each new feature.
- I consider breaking changes in that context: 
  - Returning a different output String than the previous without asking for it. That is why I decided to go with flags.  
  - Return a different object than String
  - Accept a different type other than String as input

## Decisions
 For time limitation reason I decided to exclude the following features:
- Pipeline
- Automatic generation of the documentation during release
- Automatic versioning and release


## Next Steps
- A pipeline
- Implement semantic release integrated with the pipeline
- Use conventional Commits to generate the changelog and the release notes
- Automatic versioning based on Conventional Commits